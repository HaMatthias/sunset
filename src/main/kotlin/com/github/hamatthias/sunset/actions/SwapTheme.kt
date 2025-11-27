package com.github.hamatthias.sunset.actions

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.github.hamatthias.sunset.services.theme.changer.ThemeChanger
import com.github.hamatthias.sunset.settings.SunsetSettings
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger

class SwapTheme : AnAction() {

  override fun actionPerformed(e: AnActionEvent) {
    val logger = logger<ThemeChanger>()
    val state = service<SunsetSettings>().state
    val currentTheme = LafManager.getInstance().currentUIThemeLookAndFeel.name

    if (currentTheme == state.dayTheme) {
      logger.info("Switching to night theme: ${state.nightTheme}")
      LafManager.getInstance().currentUIThemeLookAndFeel = ThemeGatherer.getThemeByName(state.nightTheme)
    } else {
      logger.info("Switching to day theme: ${state.dayTheme}")
      LafManager.getInstance().currentUIThemeLookAndFeel = ThemeGatherer.getThemeByName(state.dayTheme)
    }
    LafManager.getInstance().updateUI()
  }

  override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.EDT
}