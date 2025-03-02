package org.wikipedia.homeworks.homework09

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.wikipedia.homeworks.homework07.ExploreScreen
import org.wikipedia.homeworks.homework07.NewsCardItem
import org.wikipedia.homeworks.homework07.NewsCardViewItem
import org.wikipedia.homeworks.homework03.OnboardingScreenOld
import org.wikipedia.main.MainActivity

class NewsFlowTest : TestCase() {
    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNewsNavigation() {
        run {
            step("Skip onboarding tutorial") {
                OnboardingScreenOld.skipButton.click()
            }

            step("Find news section by title") {
                ExploreScreen.feed.childWith<NewsCardItem> {
                    withDescendant {
                        withText("In the news")
                    }
                }
            }

            step("Navigate to third news item") {
                ExploreScreen.feed.childWith<NewsCardItem> {
                    withDescendant {
                        withText("In the news")
                    }
                }.perform {
                    inTheNewsItems.childAt<NewsCardViewItem>(2) {
                        newsImage.click()
                    }
                }
            }

            step("Select second article from list") {
                NewsDetailsScreen.articlesList.childAt<ArticleItem>(1) {
                    titleView.click()
                }
            }

            step("Verify article web view is displayed") {
                ArticleScreen.contentWebView.isDisplayed()
            }
        }
    }
}