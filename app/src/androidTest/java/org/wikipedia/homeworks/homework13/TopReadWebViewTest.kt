package org.wikipedia.homeworks.homework13

import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.AutoScrollParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.wikipedia.R
import org.wikipedia.homeworks.homework07.ExploreScreen
import org.wikipedia.homeworks.homework07.TopReadCardViewItem
import org.wikipedia.homeworks.homework07.WikiCardViewItem
import org.wikipedia.homeworks.homework08.OnboardingScreen
import org.wikipedia.main.MainActivity

class TopReadWebViewTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        autoScrollParams = AutoScrollParams(allowedExceptions = emptySet())
    }
) {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun topReadTest() {
        run {
            step("Пропускаем онбординг") {
                OnboardingScreen.skipBtn.click()
            }
            step("Кликаем на вторую статью в блоке 'Top read'") {
                flakySafely(timeoutMs = 10000) {
                    ExploreScreen.feed.scrollTo {
                        withDescendant { withText(R.string.view_top_read_card_title) }
                    }

                    ExploreScreen.feed.childWith<TopReadCardViewItem> {
                        withDescendant { withText(R.string.view_top_read_card_title) }
                    }.apply {
                        isVisible()

                        flakySafely(timeoutMs = 5000) {
                            wikiCardViewItems.childAt<WikiCardViewItem>(2) {
                                cardTitle.isVisible()
                                cardTitle.click()
                            }
                        }
                    }
                }
            }
            step("Скроллим до элемента с id 'References' и проверяем его содержимое") {
                ArticleViewScreen.webView {
                    withElement(Locator.ID, "References") {
                        scroll()
                        hasText("References")
                    }
                }
            }
            step("Кликаем на ссылку в тексте с номером 5") {
                ArticleViewScreen.webView {
                    withElement(
                        Locator.XPATH,
                        "//a[contains(@class, 'reference-link') and text()='5']"
                    ) {
                        scroll()
                        click()
                    }
                }
            }
            step("Проверяем заголовок во всплывающем окне") {
                PopUpWindowViewScreen.pager.childAt<PopUpWindowItems>(0) {
                    referenceId.isVisible()
                    referenceId.containsText("5")
                }
            }
            step("Закрываем всплывающее окно") {
                device.uiDevice.pressBack()
            }
            step("Открываем вторую ссылку с классом 'mw-redirect'") {
                ArticleViewScreen.webView {
                    withElement(
                        Locator.CSS_SELECTOR, "a.mw-redirect:nth-of-type(2)"
                    ) {
                        scroll()
                        click()
                    }
                }
            }
            step("Нажимаем кнопку 'Read article'") {
                flakySafely(timeoutMs = 10000) {
                    PreviewContainer.readArticleButton.click()
                }
            }
            step("Скроллим до 'References' ещё раз") {
                ArticleViewScreen.webView {
                    withElement(Locator.ID, "References") {
                        scroll()
                        hasText("References")
                    }
                }
            }
        }
    }
}
