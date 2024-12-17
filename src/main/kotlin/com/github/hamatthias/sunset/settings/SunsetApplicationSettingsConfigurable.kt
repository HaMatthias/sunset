package com.github.hamatthias.sunset.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 * This class is called by the IntelliJ platform
 */

class SunsetApplicationSettingsConfigurable : Configurable {

  private var sunsetApplicationSettingsComponent : SunsetApplicationSettingsComponent? = null

  override fun createComponent(): JComponent {
    sunsetApplicationSettingsComponent = SunsetApplicationSettingsComponent()
    return sunsetApplicationSettingsComponent!!.getPanel()
  }

  override fun isModified(): Boolean {
    val sunsetApplicationSettingsState = SunsetApplicationSettings.getInstance().state
    return (((sunsetApplicationSettingsComponent?.getUserNameText() ?: false) != sunsetApplicationSettingsState.userId
        || (sunsetApplicationSettingsComponent?.getIdeaUserStatus()
      ?: false) != sunsetApplicationSettingsState.ideaStatus))
  }

  override fun apply() {
    val sunsetApplicationSettingsState = SunsetApplicationSettings.getInstance().state
    sunsetApplicationSettingsState.userId = sunsetApplicationSettingsComponent?.getUserNameText() ?: "No Text defined"
    sunsetApplicationSettingsState.ideaStatus = sunsetApplicationSettingsComponent?.getIdeaUserStatus() ?: false
  }

  @Nls(capitalization = Nls.Capitalization.Title)
  override fun getDisplayName(): String {
    return "My Settings Page"
  }

  override fun getPreferredFocusedComponent(): JComponent? {
    return sunsetApplicationSettingsComponent?.getPreferredFocusedComponent();
  }

  override fun reset() {
    val sunsetApplicationSettingsState = SunsetApplicationSettings.getInstance().state
    sunsetApplicationSettingsComponent?.setUserNameText(sunsetApplicationSettingsState.userId)
    sunsetApplicationSettingsComponent?.setIdeaUserStatus(sunsetApplicationSettingsState.ideaStatus)
  }

  override fun disposeUIResources() {
    sunsetApplicationSettingsComponent = null
  }


}