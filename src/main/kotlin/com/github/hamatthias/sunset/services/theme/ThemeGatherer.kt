package com.github.hamatthias.sunset.services.theme

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Suppress("UnstableApiUsage")
class ThemeGatherer {

  private val themes = LafManager.getInstance().installedThemes.toImmutableList()

  fun getThemeByName(name: String): UIThemeLookAndFeelInfo? {
    return themes.find { it.name == name }
  }

  fun getThemeNames(): ImmutableList<String> {
    themes.map { it.id }
    return themes.map { theme -> theme.name }.toImmutableList()
  }
}