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
class SunsetApplicationSettings : PersistentStateComponent<SunsetApplicationSettings.State> {

  class State {
    @NonNls
    var userId: String = "John Smith"
    var ideaStatus: Boolean = false
  }

  private var currentState: State = State()

  companion object {
    fun getInstance(): SunsetApplicationSettings {
      return ApplicationManager.getApplication()
        .getService(SunsetApplicationSettings::class.java)
    }
  }

  override fun getState(): State {
    return currentState
  }

  override fun loadState(state: State) {
    currentState = state
  }
}