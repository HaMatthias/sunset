package com.github.hamatthias.sunset.services.theme

import com.github.hamatthias.sunset.settings.SunsetSettings
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import com.intellij.openapi.diagnostic.logger
import java.time.LocalTime

class ThemeChanger {

  private val logger = logger<ThemeChanger>()

  private val settings = SunsetSettings.getInstance()

  fun applyTheme() {
    val themeToApply = getTheme()
    val currentTheme = LafManager.getInstance().currentUIThemeLookAndFeel
    logger.debug("Theme to apply: {} - Current Theme: {}", themeToApply.id, currentTheme.id)
    if (themeToApply != currentTheme) {
      logger.info("Themes are different. Change theme to" + themeToApply.name)
      LafManager.getInstance().setCurrentLookAndFeel(themeToApply, false)
    }
  }

  fun getNextThemeChange() : LocalTime {
    val timeToDayTheme = LocalTime.parse(settings.state.timeToDayTheme)
    val timeToNightTheme = LocalTime.parse(settings.state.timeToNightTheme)
    val now = LocalTime.now()

    if (timeToDayTheme.isAfter(now)) {
      return timeToDayTheme
    }
    return timeToNightTheme
  }

  private fun getTheme() : UIThemeLookAndFeelInfo {
    val timeToDayTheme = LocalTime.parse(settings.state.timeToDayTheme)
    val timeToNightTheme = LocalTime.parse(settings.state.timeToNightTheme)
    val now = LocalTime.now()

    var result = settings.state.dayTheme
    if (now.isBefore(timeToDayTheme) && now.isAfter(timeToNightTheme)) {
      result = settings.state.nightTheme
    }

    logger.debug("result{}", result)
    return ThemeGatherer.getThemeByName(result)
  }
}
