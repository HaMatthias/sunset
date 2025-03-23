package com.github.hamatthias.sunset.services.theme.changer

import com.github.hamatthias.sunset.settings.SettingsBundle

enum class ThemeChangingStrategies(val displayName: String) {
  LOCATION(SettingsBundle.setting("label.radio.strategy.location")),
  DAY_AND_NIGHT(SettingsBundle.setting("label.radio.strategy.time")),
  RANDOM(SettingsBundle.setting("label.radio.strategy.random"))
}