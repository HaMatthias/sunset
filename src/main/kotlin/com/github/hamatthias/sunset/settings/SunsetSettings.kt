package com.github.hamatthias.sunset.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NonNls

@State(
  name = "com.github.hamatthias.sunset.settings.SunsetApplicationSettings",
  storages = [Storage("SdkSettingsPlugin.xml")]
)
class SunsetSettings : PersistentStateComponent<SunsetSettings.State> {

  class State {

    val DEFAULT_LONGITUDE = "0.0"
    val DEFAULT_LATITUDE = "0.0"
    val DEFAULT_TIME_OF_DAY_THEME = "06:00"
    val DEFAULT_TIME_OF_NIGHT_THEME = "18:00"
    val DEFAULT_DAY_THEME = "Light"
    val DEFAULT_NIGHT_THEME = "Dark"

    @NonNls
    var longitude: String = DEFAULT_LONGITUDE
    var latitude: String = DEFAULT_LATITUDE
    var timeToDayTheme: String = DEFAULT_TIME_OF_DAY_THEME
    var timeToNightTheme: String = DEFAULT_TIME_OF_NIGHT_THEME
    var dayTheme: String = DEFAULT_DAY_THEME
    var nightTheme: String = DEFAULT_NIGHT_THEME
  }

  private var currentState: State = State()

  companion object {
    fun getInstance(): SunsetSettings {
      return ApplicationManager.getApplication()
        .getService(SunsetSettings::class.java)
    }
  }

  override fun getState(): State {
    return currentState
  }

  override fun loadState(state: State) {
    currentState = state
  }
}