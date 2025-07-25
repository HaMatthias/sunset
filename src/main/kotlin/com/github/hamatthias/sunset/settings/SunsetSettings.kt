package com.github.hamatthias.sunset.settings

import com.github.hamatthias.sunset.services.theme.changer.ThemeChangingStrategies
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.components.*

@Service
@State(
  name = "com.github.hamatthias.sunset.settings.SunsetSettings",
  storages = [Storage(
    "SdkSettingsPlugin.xml",
    roamingType = RoamingType.DEFAULT
  )],
  category = SettingsCategory.UI
)
class SunsetSettings : SimplePersistentStateComponent<SunsetSettings.State>(State()) {

  class State : BaseState() {
    // Strategy
    var strategy by enum(ThemeChangingStrategies.DAY_AND_NIGHT)

    // Location (With workaround for avoiding nullability)
    private var _longitude by string("0.0")
    var longitude: String
      get() = _longitude ?: "0.0"
      set(value) {
        _longitude = value
      }

    private var _latitude by string("0.0")
    var latitude: String
      get() = _latitude ?: "0.0"
      set(value) {
        _latitude = value
      }

    // Time
    var timeToDayTheme by string("06:00")
    var timeToNightTheme by string("18:00")

    // Random
    var randomEdgeInterval by property(3)

    // Theme
    var dayTheme by string(LafManager.getInstance().currentUIThemeLookAndFeel.name)
    var nightTheme by string(LafManager.getInstance().currentUIThemeLookAndFeel.name)
  }
}