package com.github.hamatthias.sunset.services.theme

import com.intellij.ide.ui.LafManager
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Suppress("UnstableApiUsage")
class ThemeGatherer {

  private val themes = LafManager.getInstance().installedThemes.toImmutableList()

  fun getDefaultLightTheme(): String {
    return themes.first { theme -> theme.name == "Light"}.name
  }

  fun getThemeNames(): ImmutableList<String> {
    return themes.map { theme -> theme.name }.toImmutableList()
  }

  fun getDarkThemeNames(): ImmutableList<String> {
    return themes.filter { theme -> theme.isDark }.map { theme -> theme.name }.toImmutableList()
  }

  fun getLightThemeNames(): ImmutableList<String> {
    return themes.filter { theme -> !theme.isDark }.map { theme -> theme.name }.toImmutableList()
  }
}