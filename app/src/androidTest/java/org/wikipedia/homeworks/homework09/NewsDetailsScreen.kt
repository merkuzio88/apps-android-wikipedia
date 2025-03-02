package org.wikipedia.homeworks.homework09

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.wikipedia.R

object NewsDetailsScreen : KScreen<NewsDetailsScreen>() {
    override val layoutId = null
    override val viewClass = null

    val toolbar = KView { withId(R.id.toolbar) }
    val headerImageView = KView { withId(R.id.header_image_view) }
    val contentText = KTextView { withId(R.id.story_text_view) }
    val articlesList = KRecyclerView (
        builder = { withId(R.id.news_story_items_recyclerview) },
        itemTypeBuilder = {
            itemType(::ArticleItem)
        }
    )
}