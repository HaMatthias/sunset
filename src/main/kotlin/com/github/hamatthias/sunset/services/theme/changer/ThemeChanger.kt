package com.github.hamatthias.sunset.services.theme.changer

import com.github.hamatthias.sunset.services.theme.ThemeChangerSchedulingService
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import java.time.LocalTime

/**
 * Theme changer interface to be applied for all strategies used for changing the themes
 */
interface ThemeChanger {

  fun applyTheme()

  fun getNextThemeChange(): LocalTime

  fun getNextTheme(): UIThemeLookAndFeelInfo

  fun scheduleNextThemeChange() {
    val logger = logger<ThemeChanger>()
    val service = service<ThemeChangerSchedulingService>()
    val nextThemeChange = getNextThemeChange()
    logger.info("Next theme change will be scheduled at: $nextThemeChange")

    service.scheduleThemeChange(nextThemeChange) { applyTheme() }
  }
}
