package com.github.hamatthias.sunset.services.theme.changer

import com.github.hamatthias.sunset.settings.SettingsBundle

// Suppress, because needed for the combo box model in the settings
@Suppress("unused")
enum class ThemeChangingStrategies(
  val displayName: String,
  val strategyImplementation: ThemeChanger
) {
  LOCATION(SettingsBundle.setting("label.radio.strategy.location"), Location),
  DAY_AND_NIGHT(SettingsBundle.setting("label.radio.strategy.time"), DayAndNight),
  RANDOM(SettingsBundle.setting("label.radio.strategy.random"), DiceRoller)
}