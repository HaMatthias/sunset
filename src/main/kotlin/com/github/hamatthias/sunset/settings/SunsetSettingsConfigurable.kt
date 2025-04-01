package com.github.hamatthias.sunset.settings

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.github.hamatthias.sunset.services.theme.changer.ThemeChangingStrategies
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.options.BoundSearchableConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.EnumComboBoxModel
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.ValidationInfoBuilder
import java.time.LocalTime
import java.time.format.DateTimeParseException
import javax.swing.DefaultComboBoxModel

/**
 * Provides controller functionality for application settings.
 * This class is called by the IntelliJ platform
 */

class SunsetSettingsConfigurable : BoundSearchableConfigurable(
  SettingsBundle.setting("label.panel.settings"),
  helpTopic = "2", // TODO
  _id = "1" // TODO
) {

  private val logger = logger<SunsetSettingsConfigurable>()

  override fun createPanel(): DialogPanel {
    val sunsetSettingsState = service<SunsetSettings>().state
    return panel {

      // Strategy
      separator().rowComment(SettingsBundle.setting("label.radio.strategies"))
      row {
        comboBox(
          model = EnumComboBoxModel(ThemeChangingStrategies::class.java),
          renderer = SimpleListCellRenderer.create("") {it.displayName}
        ).bindItem(sunsetSettingsState::strategy.toNullableProperty())
      }

      // Location
      separator().rowComment(SettingsBundle.setting("label.row.location"))
      row(SettingsBundle.setting("label.input.longitude")) {
        textField()
          .bindText({ sunsetSettingsState.longitude.toString() }, {sunsetSettingsState.longitude = it})
          .validationOnInput(::validateDecimalInput)
          .validationOnApply(::validateDecimalInput)
      }
      row(SettingsBundle.setting("label.input.latitude")) {
        textField()
          .bindText({ sunsetSettingsState.latitude.toString() }, {sunsetSettingsState.latitude = it})
          .validationOnInput(::validateDecimalInput)
          .validationOnApply(::validateDecimalInput)
      }

      // Time
      separator().rowComment(SettingsBundle.setting("label.row.time"))
      row(SettingsBundle.setting("label.input.firstChange")) {
        textField()
          .bindText({ sunsetSettingsState.timeToDayTheme.toString() }, {sunsetSettingsState.timeToDayTheme = it})
          .validationOnInput(::validateTimeInput)
          .validationOnApply(::validateTimeInput)
      }
      row(SettingsBundle.setting("label.input.secondChange")) {
        textField()
          .bindText({ sunsetSettingsState.timeToNightTheme.toString() }, {sunsetSettingsState.timeToNightTheme = it})
          .validationOnInput(::validateTimeInput)
          .validationOnApply(::validateTimeInput)
      }

      // Random
      separator().rowComment(SettingsBundle.setting("label.row.random"))
      row(SettingsBundle.setting("label.input.edgeInterval")) {
        textField()
          .bindIntText(sunsetSettingsState::randomEdgeInterval)
          .validationOnInput(::validateNumberInput)
          .validationOnApply(::validateNumberInput)
      }

      // Theme settings
      separator().rowComment(SettingsBundle.setting("label.row.theme"))
      row(SettingsBundle.setting("label.comboBox.dayTheme")) {
        comboBox(DefaultComboBoxModel(ThemeGatherer.getThemeNames().toTypedArray()))
          .bindItem(sunsetSettingsState::dayTheme)
      }
      row(SettingsBundle.setting("label.comboBox.nightTheme")) {
        comboBox(DefaultComboBoxModel(ThemeGatherer.getThemeNames().toTypedArray()))
          .bindItem(sunsetSettingsState::nightTheme)
      }
      logger.info("Returning settings panel")
    }
  }

  override fun apply() {
    val sunsetSettingsState = service<SunsetSettings>().state
    sunsetSettingsState.apply {
      super.apply()
      val strategy = sunsetSettingsState.strategy.strategyImplementation
      strategy.applyTheme()
      strategy.scheduleNextThemeChange()
    }
  }

  private fun validateTimeInput(builder: ValidationInfoBuilder, textField: JBTextField): ValidationInfo? {
    val time = textField.text
    try {
      LocalTime.parse(time)
      return null
    } catch (e: DateTimeParseException) {
      logger.info("Invalid time entered: $time")
      return builder.error(SettingsBundle.setting("input.error.time"))
    }
  }

  private fun validateDecimalInput(builder: ValidationInfoBuilder, textField: JBTextField): ValidationInfo? {
    val decimal = textField.text

    if (decimal.length > 10) {
      logger.info("Invalid decimal number entered: $decimal")
      return builder.error(SettingsBundle.setting("input.error.decimal.length"))
    }

    try {
      decimal.toFloat()
      return null
    } catch (e: NumberFormatException) {
      logger.info("Invalid decimal number entered: $decimal")
      return builder.error(SettingsBundle.setting("input.error.decimal.value"))
    }
  }

  private fun validateNumberInput(builder: ValidationInfoBuilder, textField: JBTextField): ValidationInfo? {
    val number = textField.text
    val parsedNumber: Int

    try {
      parsedNumber = number.toInt()
    } catch (e: NumberFormatException) {
      logger.info("Invalid random number entered: $number")
      return builder.error(SettingsBundle.setting("input.error.random.value"))
    }

    if (parsedNumber > 24) {
      logger.info("Random number too long: $number")
      return builder.error(SettingsBundle.setting("input.error.random.tooLarge"))
    }

    return null
  }
}