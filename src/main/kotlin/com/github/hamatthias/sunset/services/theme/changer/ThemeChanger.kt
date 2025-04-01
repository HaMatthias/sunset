package com.github.hamatthias.sunset.services.theme.changer

import com.intellij.ide.ui.laf.UIThemeLookAndFeelInfo
import java.time.LocalTime

/**
 * Theme changer interface to be applied for all strategies used for changing the themes
 */
interface ThemeChanger {

  fun applyTheme()

  fun getNextThemeChange(): LocalTime

  fun getNextTheme(): UIThemeLookAndFeelInfo
  }
}
