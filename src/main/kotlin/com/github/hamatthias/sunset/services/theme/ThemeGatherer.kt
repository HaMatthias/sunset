package com.github.hamatthias.sunset.services.theme

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo

@Suppress("UnstableApiUsage")
object ThemeGatherer {

  private val themes = LafManager.getInstance().installedThemes

  fun getThemeByName(name: String?): UIThemeLookAndFeelInfo {
    return themes.find { it.name == name } ?: LafManager.getInstance().currentUIThemeLookAndFeel
  }

  fun getThemeNames(): List<String> {
    return themes.map { it.name }.toList()
  }
}
