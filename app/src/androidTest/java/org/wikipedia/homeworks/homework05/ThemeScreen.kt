package org.wikipedia.homeworks.homework05

import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textview.MaterialTextView
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.progress.KSeekBar
import io.github.kakaocup.kakao.switch.KSwitch
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.wikipedia.R
import org.wikipedia.views.DiscreteSeekBar

val readingCategoryTitle = KTextView {
    withId(R.id.textSettingsCategory)
    withText(R.string.theme_category_reading)
    isInstanceOf(MaterialTextView::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val currentTextSize = KTextView {
    withId(R.id.text_size_percent)
    isInstanceOf(MaterialTextView::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val decreaseTextButton = KButton {
    withId(R.id.buttonDecreaseTextSize)
    withText(R.string.text_size_decrease)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val textSizeSlider = KSeekBar {
    withId(R.id.text_size_seek_bar)
    isEnabled()
    isInstanceOf(DiscreteSeekBar::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val increaseTextButton = KButton {
    withId(R.id.buttonIncreaseTextSize)
    withText(R.string.text_size_increase)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val sansSerifOption = KButton {
    withId(R.id.button_font_family_sans_serif)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val serifOption = KButton {
    withId(R.id.button_font_family_serif)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val focusModeIcon = KImageView {
    withParent { withId(R.id.readingFocusModeContainer) }
    withDrawable(R.drawable.ic_icon_reading_focus_mode)
    isInstanceOf(AppCompatImageView::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val focusModeSwitch = KSwitch {
    withId(R.id.theme_chooser_reading_focus_mode_switch)
    withText(R.string.reading_focus_mode)
    isInstanceOf(MaterialSwitch::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val focusModeDescription = KTextView {
    withId(R.id.theme_chooser_reading_focus_mode_description)
    withText(R.string.reading_focus_mode_detail)
    isInstanceOf(MaterialTextView::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val themeSelectionTitle = KTextView {
    withText(R.string.color_theme_select)
    isInstanceOf(MaterialTextView::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val lightThemeButton = KButton {
    withId(R.id.button_theme_light)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val sepiaThemeButton = KButton {
    withId(R.id.button_theme_sepia)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val darkThemeButton = KButton {
    withId(R.id.button_theme_dark)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val blackThemeButton = KButton {
    withId(R.id.button_theme_black)
    isInstanceOf(MaterialButton::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val systemThemeSwitch = KSwitch {
    withId(R.id.theme_chooser_match_system_theme_switch)
    withText(R.string.theme_chooser_dialog_match_system_theme_switch_label)
    isInstanceOf(MaterialSwitch::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}

val imageDimmingSwitch = KSwitch {
    withId(R.id.theme_chooser_dark_mode_dim_images_switch)
    isInstanceOf(MaterialSwitch::class.java)
    withParent { isInstanceOf(LinearLayout::class.java) }
}
