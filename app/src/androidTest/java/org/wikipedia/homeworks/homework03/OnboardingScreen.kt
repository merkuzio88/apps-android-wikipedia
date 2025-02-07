package org.wikipedia.homeworks.homework03

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import org.wikipedia.views.AppTextView

// Logo Wikipedia
val wikiLogoImage = listOf(
    AppCompatImageView::class.java,
    "imageViewCentered",
    null
)

// Заголовок
val encyclopediaTitleText = listOf(
    AppTextView::class.java,
    "primaryTextView",
    "onboarding_welcome_title_v2"
)

// Подзаголовок
val deviceLanguagesText = listOf(
    AppTextView::class.java,
    "secondaryTextView",
    "onboarding_multilingual_secondary_text"
)

// Список языков (контейнер)
val languagesList = listOf(
    RecyclerView::class.java,
    "languagesList",
    null
)

// Кнопка "Add or edit languages"
val addLanguagesButton = listOf(
    MaterialButton::class.java,
    "addLanguageButton",
    "onboarding_multilingual_add_language_text"
)

// Индикатор прогресса (точки)
val progressIndicator = listOf(
    TabLayout::class.java,
    "view_onboarding_page_indicator",
    null
)

// Кнопка Skip
val skipButton = listOf(
    MaterialButton::class.java,
    "fragment_onboarding_skip_button",
    "onboarding_skip"
)

// Кнопка Continue
val continueButton = listOf(
    MaterialButton::class.java,
    "fragment_onboarding_forward_button",
    "onboarding_continue"
)

// Кнопка Done
val doneButton = listOf(
    MaterialButton::class.java,
    "fragment_onboarding_done_button",
    "onboarding_get_started"
)
