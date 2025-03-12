package com.github.hamatthias.sunset.services.theme.changer

import com.github.hamatthias.sunset.services.theme.ThemeGatherer
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import com.intellij.openapi.diagnostic.logger
import java.time.LocalTime
import kotlin.random.Random

/**
 * Applies a random theme every x hours. x is randomly calculated with every theme change between 1-6 hours.
 */
class DiceRoller : ThemeChanger {

  private val logger = logger<DiceRoller>()

  override fun applyTheme() {
    val current : UIThemeLookAndFeelInfo = LafManager.getInstance().currentUIThemeLookAndFeel
    val random: String? = ThemeGatherer.getThemeNames().toList().randomOrNull()
    val result : UIThemeLookAndFeelInfo = ThemeGatherer.getThemeByName(random)

    logger.debug("old theme={}, new theme={}", current, result)
    LafManager.getInstance().setCurrentLookAndFeel(result, false)
  }

  override fun getNextThemeChange() : LocalTime {
    val hoursToAdd = Random.nextInt(1, 7)
    return LocalTime.now().plusHours(hoursToAdd.toLong())
  }
}
