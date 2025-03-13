package com.github.hamatthias.sunset.settings

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.layout.ValidationInfoBuilder
import java.time.LocalTime
import java.time.format.DateTimeParseException
import javax.swing.JComponent

/**
 * Supports creating and managing a JPanel for the Settings Dialog.
 */
class SunsetSettingsComponent() {

  private val themeGatherer = ThemeGatherer()

  private val longitudeInput = JBTextField()
  private val latitudeInput = JBTextField()
  private val timeToDayThemeInput = JBTextField()
  private val timeToNightThemeInput = JBTextField()
  private val dayThemeComboBox = ComboBox<String>()
  private val nightThemeComboBox = ComboBox<String>()

  fun getPanel(): DialogPanel {
    setUpDayThemeComboBox()
    setUpNightThemeComboBox()
    return panel {
      separator().rowComment(SettingsBundle.setting("label.row.location_and_time"))
      row(SettingsBundle.setting("label.input.longitude")) {
        cell(longitudeInput)
        cell(timeToDayThemeInput).validationOnInput(::validateTimeInput).validationOnApply(::validateTimeInput)
      }
      row(SettingsBundle.setting("label.input.latitude")) {
        cell(latitudeInput)
        cell(timeToNightThemeInput).validationOnInput(::validateTimeInput).validationOnApply(::validateTimeInput)
      }
      separator().rowComment(SettingsBundle.setting("label.row.theme"))
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

  fun getTimeToDayTheme(): String {
    return timeToDayThemeInput.text
  }

  fun setTimeToDayTheme(timeToDayTheme: String) {
    timeToDayThemeInput.text = timeToDayTheme
  }

  fun getTimeToNightTheme(): String {
    return timeToNightThemeInput.text
  }

  fun setTimeToNightTheme(timeToNightTheme: String) {
    timeToNightThemeInput.text = timeToNightTheme
  }

  fun getDayThemeComboBoxItem(): String {
    return dayThemeComboBox.selectedItem as String
  }

  fun setDayThemeComboBoxItem(name: String) {
    dayThemeComboBox.selectedItem = name
  }

  fun getNightThemeComboBoxItem(): String {
    return nightThemeComboBox.selectedItem as String
  }

  fun setNightThemeComboBoxItem(name: String) {
    nightThemeComboBox.selectedItem = name
  }

  fun getPreferredFocusedComponent(): JComponent {
    return longitudeInput
  }

  private fun validateTimeInput(builder: ValidationInfoBuilder, textField: JBTextField): ValidationInfo? {
    val time = textField.text
    try {
      LocalTime.parse(time)
      return null
    } catch (e: DateTimeParseException) {
      return builder.error(SettingsBundle.setting("label.input.error.time"))
    }
  }
}