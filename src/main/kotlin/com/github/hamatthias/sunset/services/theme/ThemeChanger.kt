package com.github.hamatthias.sunset.services.theme

import com.github.hamatthias.sunset.settings.SunsetSettings
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import com.intellij.openapi.diagnostic.logger
import java.time.LocalTime

class ThemeChanger {

  private val logger = logger<ThemeChanger>()

  private val themeGatherer = ThemeGatherer()
  private val settings = SunsetSettings.getInstance()

  private var timer: Timer? = null
  private val transitionStep = 10

  fun switchThemeIfNeeded() {
    val themeToApply = getThemeToApply() ?: return
    val currentTheme = LafManager.getInstance().currentUIThemeLookAndFeel
    logger.debug("Theme to apply: {} - Current Theme: {}", themeToApply.id, currentTheme.id)
    if (themeToApply != currentTheme) {
      logger.info("Themes are different. Change theme to" + themeToApply.name)
      LafManager.getInstance().setCurrentLookAndFeel(themeToApply, false)
    }
  }

  fun switchTheme() {
    val method = "switchTheme()"
    val currentTheme = LafManager.getInstance().currentUIThemeLookAndFeel
    val dayTheme = themeGatherer.getThemeByName(settings.state.dayTheme)
    val nightTheme = themeGatherer.getThemeByName(settings.state.nightTheme)
    var nextTheme = dayTheme

    if (currentTheme == dayTheme) {
      nextTheme = nightTheme
    }

    if (nextTheme != null) {
      logger.info(method + "to " + nextTheme.name)
      LafManager.getInstance().setCurrentLookAndFeel(nextTheme, false)
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

  private fun getThemeToApply() : UIThemeLookAndFeelInfo? {
    val timeToDayTheme = LocalTime.parse(settings.state.timeToDayTheme)
    val timeToNightTheme = LocalTime.parse(settings.state.timeToNightTheme)
    val now = LocalTime.now()

    var themeName = settings.state.dayTheme

    if (now.isAfter(timeToDayTheme)) {
      logger.debug("It is later than {}", timeToDayTheme)
      if (now.isAfter(timeToNightTheme)) {
        logger.debug("It is later than {} too. Switch to the night theme", timeToNightTheme)
        themeName = settings.state.nightTheme
      }
    } else {
      logger.debug("It is earlier than {}. Switch to night theme", timeToDayTheme)
      themeName = settings.state.nightTheme
    }
    return themeGatherer.getThemeByName(themeName)
  }
}