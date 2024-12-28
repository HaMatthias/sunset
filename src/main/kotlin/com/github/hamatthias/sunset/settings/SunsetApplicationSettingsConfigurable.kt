package com.github.hamatthias.sunset.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 * This class is called by the IntelliJ platform
 */

class SunsetApplicationSettingsConfigurable : Configurable {

  private var sunsetApplicationSettingsComponent = SunsetApplicationSettingsComponent()

  override fun createComponent(): JComponent {
    return sunsetApplicationSettingsComponent.getPanel()
  }

  override fun isModified(): Boolean {
    val sunsetApplicationSettingsState = SunsetApplicationSettings.getInstance().state
    return (sunsetApplicationSettingsComponent.getUserNameText() != sunsetApplicationSettingsState.userId
        || sunsetApplicationSettingsComponent.getIdeaUserStatus() != sunsetApplicationSettingsState.ideaStatus)
  }

  override fun apply() {
    val sunsetApplicationSettingsState = SunsetApplicationSettings.getInstance().state
    sunsetApplicationSettingsState.userId = sunsetApplicationSettingsComponent.getUserNameText()
    sunsetApplicationSettingsState.ideaStatus = sunsetApplicationSettingsComponent.getIdeaUserStatus()
  }

  @Nls(capitalization = Nls.Capitalization.Title)
  override fun getDisplayName(): String {
    return "My Settings Page"
  }

  override fun getPreferredFocusedComponent(): JComponent {
    return sunsetApplicationSettingsComponent.getPreferredFocusedComponent();
  }

  override fun reset() {
    val sunsetApplicationSettingsState = SunsetApplicationSettings.getInstance().state
    sunsetApplicationSettingsComponent.setUserNameText(sunsetApplicationSettingsState.userId)
    sunsetApplicationSettingsComponent.setIdeaUserStatus(sunsetApplicationSettingsState.ideaStatus)
  }
}