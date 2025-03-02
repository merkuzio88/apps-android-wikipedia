package org.wikipedia.homeworks.homework09

import android.view.View
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import org.wikipedia.R

class ArticleItem(matcher: Matcher<View>) : KRecyclerItem<ArticleItem>(matcher) {
    val numberIndicator = KView(matcher) { withId(R.id.view_list_card_number) }
    val titleView = KTextView(matcher) { withId(R.id.view_list_card_item_title) }
    val subtitleView = KTextView(matcher) { withId(R.id.view_list_card_item_subtitle) }
    val graphView = KView(matcher) { withId(R.id.view_list_card_item_graph) }
    val viewsCounter = KTextView(matcher) { withId(R.id.view_list_card_item_pageviews) }
    val thumbnailView = KView(matcher) { withId(R.id.view_list_card_item_image) }
}