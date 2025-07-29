package com.github.hamatthias.sunset.services.theme.changer

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.github.hamatthias.sunset.settings.SunsetSettings
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import dev.jamesyox.kastro.sol.SolarEvent
import dev.jamesyox.kastro.sol.SolarEventSequence
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDateTime

object SolarEvent : ThemeChanger {

  private val logger = logger<com.github.hamatthias.sunset.services.theme.changer.SolarEvent>()

  override fun applyTheme() {
    val themeToApply = getThemeToApply()
    val currentTheme = LafManager.getInstance().currentUIThemeLookAndFeel
    logger.debug("Theme be applied: ${themeToApply.id} - Current Theme: ${currentTheme.id}")
    if (themeToApply != currentTheme) {
      logger.info("Themes are different. Change theme to ${themeToApply.id}")
      LafManager.getInstance().currentUIThemeLookAndFeel = themeToApply
      LafManager.getInstance().updateUI()
    }

    // And now schedule the next theme change
    scheduleNextThemeChange()
  }

  override fun getNextThemeChange(): LocalDateTime {
    val settings = service<SunsetSettings>()
    val longitude = settings.state.longitude.toDouble()
    val latitude = settings.state.latitude.toDouble()
    val now = Clock.System.now()

    val nextSolarEvent = SolarEventSequence(
      start = now,
      latitude = latitude,
      longitude = longitude,
      requestedSolarEvents = listOf(SolarEvent.Sunrise, SolarEvent.Sunset)
    ).first()

    val nextThemeChange = nextSolarEvent.time.toLocalDateTime(TimeZone.currentSystemDefault())
    logger.info("Next solar event is a ${nextSolarEvent::class.simpleName} at: $nextThemeChange")

    // Add second to not interfere with an ongoing solar change
    return nextThemeChange.toJavaLocalDateTime().plusSeconds(1)
  }

  override fun getThemeToApply(): UIThemeLookAndFeelInfo {
    val settings = service<SunsetSettings>()
    val longitude = settings.state.longitude.toDouble()
    val latitude = settings.state.latitude.toDouble()
    val now = Clock.System.now()

    val nextSolarEvent = SolarEventSequence(
      start = now,
      latitude = latitude,
      longitude = longitude,
      requestedSolarEvents = listOf(SolarEvent.Sunrise, SolarEvent.Sunset)
    ).first()

    var theme = settings.state.dayTheme
    if (nextSolarEvent is SolarEvent.Sunrise) {
      theme = settings.state.nightTheme
    }

    logger.info("Theme '$theme' needs to be applied")
    return ThemeGatherer.getThemeByName(theme)
  }
}