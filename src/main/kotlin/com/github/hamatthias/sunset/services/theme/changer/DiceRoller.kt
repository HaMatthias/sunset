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
object DiceRoller : ThemeChanger {

  private val logger = logger<DiceRoller>()

  override fun applyTheme() {
    val current = LafManager.getInstance().currentUIThemeLookAndFeel
    val nextTheme = getNextTheme()

    logger.debug("old theme=$current, next theme=$nextTheme")
    LafManager.getInstance().currentUIThemeLookAndFeel = nextTheme
    LafManager.getInstance().updateUI()
  }

  override fun getNextThemeChange() : LocalTime {
    val hoursToAdd = Random.nextInt(1, 7)
    return LocalTime.now().plusHours(hoursToAdd.toLong())
  }

  override fun getNextTheme(): UIThemeLookAndFeelInfo {
    val random = ThemeGatherer.getThemeNames().toList().randomOrNull()
    return ThemeGatherer.getThemeByName(random)
  }
}
