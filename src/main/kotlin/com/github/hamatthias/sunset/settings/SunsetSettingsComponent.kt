package com.github.hamatthias.sunset.settings

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

/**
 * Supports creating and managing a JPanel for the Settings Dialog.
 */
class SunsetSettingsComponent() {

  private val settings = SunsetSettings.getInstance()
  private val themeGatherer = ThemeGatherer()

  private val longitudeInput = JBTextField()
  private val latitudeInput = JBTextField()
  private val dayThemeComboBox = ComboBox<String>()
  private val nightThemeComboBox = ComboBox<String>()

  fun getPanel(): DialogPanel {
    setUpDayThemeComboBox()
    setUpNightThemeComboBox()
    return panel {
      separator().rowComment("Location")
      row(SettingsBundle.setting("label.input.longitude")) {
        cell(longitudeInput)
      }
      row(SettingsBundle.setting("label.input.latitude")) {
        cell(latitudeInput)
      }
      separator().rowComment("Themes")
      row(SettingsBundle.setting("label.comboBox.dayTheme")) {
        cell(dayThemeComboBox)
      }
      row(SettingsBundle.setting("label.comboBox.nightTheme")) {
        cell(nightThemeComboBox)
      }
    }
  }

  private fun setUpDayThemeComboBox() {
    val themeNames = themeGatherer.getThemeNames()
    themeNames.forEach {
      dayThemeComboBox.addItem(it)
    }
  }

  private fun setUpNightThemeComboBox() {
    val themeNames = themeGatherer.getThemeNames()
    themeNames.forEach {
      nightThemeComboBox.addItem(it)
    }
  }

  fun getLongitudeText(): String {
    return longitudeInput.text
  }

  fun setLongitudeText(longitude : String) {
    longitudeInput.text = longitude
  }

  fun getLatitudeText(): String {
    return latitudeInput.text
  }

  fun setLatitudeText(latitude : String) {
    latitudeInput.text = latitude
  }

  fun getDayThemeComboBoxItem(): Any {
    return dayThemeComboBox.selectedItem ?: "<None>"
  }

  fun setDayThemeComboBoxItem(item: String) {
    dayThemeComboBox.selectedItem = item
  }

  fun getNightThemeComboBoxItem(): Any {
    return nightThemeComboBox.selectedItem ?: "<None>"
  }

  fun setNightThemeComboBoxItem(item: String) {
    nightThemeComboBox.selectedItem = item
  }

  fun getPreferredFocusedComponent(): JComponent {
    return longitudeInput
  }
}