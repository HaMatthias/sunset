package com.github.hamatthias.sunset.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel
import com.intellij.ui.dsl.builder.panel


/**
 * Supports creating and managing a JPanel for the Settings Dialog.
 */
class SunsetSettingsComponent() {

  private val settings = SunsetSettings.getInstance()

  private var myMainPanel: JPanel
  private val myUserNameText = JBTextField()
  private val myIdeaUserStatus = JBCheckBox("IntelliJ IDEA user")

  private val longitudeInput : JBTextField = JBTextField()
  private val latitudeInput : JBTextField = JBTextField()

  init {
    myMainPanel = FormBuilder.createFormBuilder()
      .addLabeledComponent(JBLabel("User name:"), myUserNameText, 1, false)
      .addComponent(myIdeaUserStatus, 1)
      .addComponentFillVertically(JPanel(), 0)
      .panel
  }

  fun getPanel(): JPanel {
    return panel {
      row() {
        label("Add your location: ")
      }
      row("Längengrad") {
        textField()
      }
      row("Breitengrad") {
        textField()
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