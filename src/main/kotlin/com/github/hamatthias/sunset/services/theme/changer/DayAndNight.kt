package com.github.hamatthias.sunset.services.theme.changer

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.github.hamatthias.sunset.settings.SunsetSettings
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import java.time.LocalTime

object DayAndNight : ThemeChanger {

  private val logger = logger<DayAndNight>()

  override fun applyTheme() {
    val themeToApply = getNextTheme()
    val currentTheme = LafManager.getInstance().currentUIThemeLookAndFeel
    logger.debug("Theme to apply: ${themeToApply.id} - Current Theme: ${currentTheme.id}")
    if (themeToApply != currentTheme) {
      logger.info("Themes are different. Change theme to ${themeToApply.name}")
      LafManager.getInstance().currentUIThemeLookAndFeel = themeToApply
      LafManager.getInstance().updateUI()
    }

    // And now schedule the next theme change
    scheduleNextThemeChange()
  }

  override fun getNextThemeChange() : LocalTime {
    val settings = service<SunsetSettings>()
    val timeToDayTheme = LocalTime.parse(settings.state.timeToDayTheme)
    val timeToNightTheme = LocalTime.parse(settings.state.timeToNightTheme)
    val now = LocalTime.now()

    if (timeToDayTheme.isAfter(now)) {
      return timeToDayTheme
    }
    return timeToNightTheme
  }

  override fun getNextTheme(): UIThemeLookAndFeelInfo {
    val settings = service<SunsetSettings>()
    val timeToDayTheme = LocalTime.parse(settings.state.timeToDayTheme)
    val timeToNightTheme = LocalTime.parse(settings.state.timeToNightTheme)
    val now = LocalTime.now()

    var result = settings.state.dayTheme
    if (now.isBefore(timeToDayTheme) || now.isAfter(timeToNightTheme)) {
      result = settings.state.nightTheme
    }

    logger.debug("result: $result")
    return ThemeGatherer.getThemeByName(result)
  }
}
