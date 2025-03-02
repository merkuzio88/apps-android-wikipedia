package org.wikipedia.homeworks.homework08

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.wikipedia.main.MainActivity

class OnboardingScreenOldTests : TestCase() {

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testWikiLogoVisibility() {
        run {
            step("Check logo visibility on all pages") {
                repeat(4) { page ->
                    OnboardingScreen.viewPager.childAt<OnboardingPageItem>(page) {
                        imageView.isDisplayed()
                    }
                    if (page < 3) {
                        OnboardingScreen.continueBtn.click()
                    }
                }
            }
        }
    }

    @Test
    fun testTextFieldsVisibility() {
        run {
            step("Check text fields visibility") {
                OnboardingScreen.viewPager.childAt<OnboardingPageItem>(0) {
                    primaryInfoText.isDisplayed()
                    secondaryInfoText.isDisplayed()
                }
                OnboardingScreen.viewPager.childAt<OnboardingPageItem>(1) {
                    primaryInfoText.isDisplayed()
                    secondaryInfoText.isDisplayed()
                }
            }
        }
    }

    @Test
    fun testNavigationButtons() {
        run {
            step("Check navigation buttons") {
                OnboardingScreen.skipBtn.isDisplayed()
                OnboardingScreen.skipBtn.isClickable()
                OnboardingScreen.continueBtn.isDisplayed()
                OnboardingScreen.continueBtn.isClickable()
                OnboardingScreen.getStartedBtn.isGone()
            }

            step("Navigate to last page") {
                repeat(3) {
                    OnboardingScreen.continueBtn.click()
                }
                OnboardingScreen.getStartedBtn.isDisplayed()
                OnboardingScreen.getStartedBtn.isClickable()
            }
        }
    }

    @Test
    fun testLanguageSection() {
        run {
            step("Check language section on first page") {
                OnboardingScreen.viewPager.childAt<OnboardingPageItem>(0) {
                    items.isDisplayed()
                    addLanguageBtn.isDisplayed()
                    addLanguageBtn.isClickable()
                }
            }

            step("Check language button functionality") {
                OnboardingScreen.viewPager.childAt<OnboardingPageItem>(0) {
                    addLanguageBtn.click()
                }
            }
        }
    }

    @Test
    fun testTabLayout() {
        run {
            step("Check tab layout visibility and basic navigation") {
                OnboardingScreen.tabLayout.isDisplayed()

                OnboardingScreen.continueBtn.click()
                OnboardingScreen.tabLayout.isDisplayed()

                OnboardingScreen.continueBtn.click()
                OnboardingScreen.tabLayout.isDisplayed()

                OnboardingScreen.continueBtn.click()
                OnboardingScreen.tabLayout.isDisplayed()
            }
        }
    }

    @Test
    fun testSkipNavigation() {
        run {
            step("Check skip button navigation") {
                OnboardingScreen.skipBtn.click()
            }
        }
    }

    @Test
    fun testCompleteOnboarding() {
        run {
            step("Complete full onboarding flow") {
                repeat(3) {
                    OnboardingScreen.continueBtn.click()
                }
                OnboardingScreen.getStartedBtn.click()
            }
        }
    }

    @Test
    fun testDefaultLanguageList() {
        run {
            step("Check default language list") {
                OnboardingScreen.viewPager.childAt<OnboardingPageItem>(0) {
                    items.isDisplayed()
                }
            }
        }
    }
}
