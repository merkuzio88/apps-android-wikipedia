package org.wikipedia.homeworks.homework09

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.wikipedia.R

object ArticleScreen : KScreen<ArticleScreen>() {
    override val layoutId = null
    override val viewClass = null

    val contentWebView = KView { withId(R.id.page_web_view) }
    val toolbarView = KView { withId(R.id.page_toolbar) }
    val featureImage = KView { withId(R.id.articleImage) }
    val headlineText = KTextView { withId(R.id.articleTitle) }
    val summaryText = KTextView { withId(R.id.articleDescription) }
    val searchButton = KView { withId(R.id.page_toolbar_button_search) }
    val tabsButton = KButton { withId(R.id.page_toolbar_button_tabs) }
    val alertsButton = KButton { withId(R.id.page_toolbar_button_notifications) }
    val menuButton = KButton { withId(R.id.page_toolbar_button_show_overflow_menu) }
}