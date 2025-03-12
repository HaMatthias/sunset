package com.github.hamatthias.sunset.services.theme.changer

import java.time.LocalTime

interface ThemeChanger {

    fun applyTheme()

    fun getNextThemeChange() : LocalTime
}
