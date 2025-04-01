package com.github.hamatthias.sunset.settings

import com.github.hamatthias.sunset.services.theme.changer.ThemeChangingStrategies
import com.intellij.openapi.components.*

@Service
@State(
  name = "com.github.hamatthias.sunset.settings.SunsetSettings",
  storages = [Storage("SdkSettingsPlugin.xml")]
)
class SunsetSettings : SimplePersistentStateComponent<SunsetSettings.State>(State()) {

  class State : BaseState() {
    // Strategy
    var strategy by enum(ThemeChangingStrategies.DAY_AND_NIGHT)

    // Location
    var longitude by string("0.0")
    var latitude by string("0.0")

    // Time
    var timeToDayTheme by string("06:00")
    var timeToNightTheme by string("18:00")

    // Random
    var randomEdgeInterval by property(3)

    // Theme
    var dayTheme by string("Light")
    var nightTheme by string("Dark")
  }
}