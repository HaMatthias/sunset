package com.github.hamatthias.sunset.settings

import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.bindText
import javax.swing.JComponent
import javax.swing.JPanel
import com.intellij.ui.dsl.builder.panel

/**
 * Supports creating and managing a JPanel for the Settings Dialog.
 */
class SunsetSettingsComponent() {

  private val settings : SunsetSettings = SunsetSettings.getInstance()

  private var longitudeInput = JBTextField()
  private val latitudeInput = JBTextField()

  fun getPanel(): JPanel {
    return panel {
      row() {
        label("Add your location: ")
          .comment("Provide double values e.g.: 14.234342")
      }
      row(SettingsBundle.setting("label.input.longitude")) {
        textField()
          .bindText(settings.state::longitude)
      }
      row(SettingsBundle.setting("label.input.latitude")) {
        textField()
          .bindText(settings.state::latitude)
      }
      row {
      }
    }
  }

  fun getLongitudeText(): String {
    return longitudeInput.text
  }

  fun setLongitudeText(longitude : String) {
    longitudeInput.text = longitude
  }

  fun getLatitudeText(): String {
    return latitudeInput.text
  }

  fun setLatitudeText(latitude : String) {
    latitudeInput.text = latitude
  }

  fun getPreferredFocusedComponent(): JComponent {
    return longitudeInput
  }

}