package com.github.hamatthias.sunset.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 * This class is called by the IntelliJ platform
 */

class SunsetSettingsConfigurable : Configurable {

  private var sunsetSettingsComponent = SunsetSettingsComponent()

  override fun createComponent(): JComponent {
    return sunsetSettingsComponent.getPanel()
  }

  override fun isModified(): Boolean {
    val sunsetSettingsState = SunsetSettings.getInstance().state
    return (
        sunsetSettingsComponent.getLongitudeText() != sunsetSettingsState.longitude
        || sunsetSettingsComponent.getLatitudeText() != sunsetSettingsState.latitude
        || sunsetSettingsComponent.getTimeToDayTheme() != sunsetSettingsState.timeToDayTheme
        || sunsetSettingsComponent.getTimeToNightTheme() != sunsetSettingsState.timeToNightTheme
        || sunsetSettingsComponent.getDayThemeComboBoxItem() == sunsetSettingsState.dayTheme
        || sunsetSettingsComponent.getNightThemeComboBoxItem() == sunsetSettingsState.nightTheme)
  }

  override fun apply() {
    val sunsetSettingsState = SunsetSettings.getInstance().state

    // Location
    sunsetSettingsState.longitude = sunsetSettingsComponent.getLongitudeText()
    sunsetSettingsState.latitude = sunsetSettingsComponent.getLatitudeText()

    // Time
    sunsetSettingsState.timeToDayTheme = sunsetSettingsComponent.getTimeToDayTheme()
    sunsetSettingsState.timeToNightTheme = sunsetSettingsComponent.getTimeToNightTheme()

    // Theme
    sunsetSettingsState.dayTheme = sunsetSettingsComponent.getDayThemeComboBoxItem()
    sunsetSettingsState.nightTheme = sunsetSettingsComponent.getNightThemeComboBoxItem()
  }

  @Nls(capitalization = Nls.Capitalization.Title)
  override fun getDisplayName(): String {
    return "Sunset Settings"
  }

  override fun getPreferredFocusedComponent(): JComponent {
    return sunsetSettingsComponent.getPreferredFocusedComponent();
  }

  override fun reset() {
    val sunsetSettingsState = SunsetSettings.getInstance().state
    sunsetSettingsComponent.setLongitudeText(sunsetSettingsState.longitude)
    sunsetSettingsComponent.setLatitudeText(sunsetSettingsState.latitude)
    sunsetSettingsComponent.setTimeToDayTheme(sunsetSettingsState.timeToDayTheme.toString())
    sunsetSettingsComponent.setTimeToNightTheme(sunsetSettingsState.timeToNightTheme.toString())
    sunsetSettingsComponent.setDayThemeComboBoxItem(sunsetSettingsState.dayTheme)
    sunsetSettingsComponent.setNightThemeComboBoxItem(sunsetSettingsState.nightTheme)
  }
}