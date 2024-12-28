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
    @NonNls
    var longitude: String = "0.0"
    var latitude: String = "0.0"
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