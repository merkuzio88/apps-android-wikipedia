package org.wikipedia.homeworks.homework11

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.uiautomator.UiSelector
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.wikipedia.homeworks.homework07.ExploreScreen
import org.wikipedia.homeworks.homework07.NewsCardItem
import org.wikipedia.homeworks.homework07.NewsCardViewItem
import org.wikipedia.homeworks.homework08.OnboardingPageItem
import org.wikipedia.homeworks.homework08.OnboardingScreen
import org.wikipedia.homeworks.homework09.ArticleItem
import org.wikipedia.homeworks.homework09.ArticleScreen
import org.wikipedia.homeworks.homework09.NewsDetailsScreen
import org.wikipedia.main.MainActivity

class DeviceTest : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun deviceTest() {
        before {
            device.network.disable()
        }.after {
            device.network.toggleWiFi(true)
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        }.run {
            step("Поворот экрана и проверка ориентации") {
                device.exploit.setOrientation(Exploit.DeviceOrientation.Landscape)
                Thread.sleep(1000)
                if (!device.uiDevice.isNaturalOrientation) {
                    device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
                }
            }

            step("Выключение экрана, включение экрана и проверка отображения элемента") {
                device.uiDevice.sleep()
                Thread.sleep(1000)
                device.uiDevice.wakeUp()
                OnboardingScreen {
                    viewPager.isDisplayed()
                    viewPager.childAt<OnboardingPageItem>(0) {
                        imageView.isDisplayed()
                    }
                }
            }

            step("Свернуть приложение и развернуть, проверить отображение элемента") {
                device.uiDevice.pressHome()
                device.uiDevice.pressRecentApps()
                Thread.sleep(1000)
                device.uiDevice.pressRecentApps()
                OnboardingScreen {
                    skipBtn.isDisplayed()
                }
            }

            step("Переходим на News screen") {
                OnboardingScreen {
                    skipBtn.click()
                }
                device.uiDevice.waitForIdle()
                Thread.sleep(1000)
            }

            step("Открываем новость") {
                ExploreScreen.feed.childWith<NewsCardItem> {
                    withDescendant {
                        withText("In the news")
                    }
                }.perform {
                    inTheNewsItems.childAt<NewsCardViewItem>(0) {
                        newsImage.click()
                    }
                }
            }

            step("Выключить сеть, проверить отображение ошибки и кнопки Retry") {
                device.network.toggleWiFi(false)

                NewsDetailsScreen {
                    articlesList.childAt<ArticleItem>(1) {
                        titleView.click()
                    }
                }

                if (device.uiDevice.findObject(UiSelector().text("Retry")).exists()) {
                    device.network.toggleWiFi(true)
                    device.uiDevice.waitForIdle()
                    Thread.sleep(5000)
                    device.uiDevice.findObject(UiSelector().text("Retry")).click()
                }
            }

            step("Проверка элемента статьи") {
                ArticleScreen {
                    headlineText.hasAnyText()
                }
            }

            step("Проверяем, что сейчас активна MainActivity") {
                device.activities.isCurrent(MainActivity::class.java)
            }
        }
    }
}