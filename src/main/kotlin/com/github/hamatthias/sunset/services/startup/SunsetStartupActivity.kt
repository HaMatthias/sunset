package com.github.hamatthias.sunset.services.startup

import com.github.hamatthias.sunset.services.theme.ThemeChanger
import com.github.hamatthias.sunset.services.theme.ThemeChangerSchedulingService
import com.intellij.openapi.application.EDT
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This class is responsible for taking detached action on Application startup.
 *
 * The action taken consist of applying a theme if it needs to be applied because its time to change is overdue
 * and the second action is to schedule the next theme change.
 *
 * @see [Application Startup Documentation](https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open)
 * @see [Spaces Break KDoc URLs](https://youtrack.jetbrains.com/issue/KTIJ-12442/KDoc-Support-clickable-URLs-highlighting-navigation)
 */
class SunsetStartupActivity : ProjectActivity {

  override suspend fun execute(project: Project) {
    withContext(Dispatchers.EDT) {
      // Apply theme directly on startup
      val themeChanger = ThemeChanger()
      themeChanger.applyTheme()

      // Schedule the next theme change
      val service = project.service<ThemeChangerSchedulingService>()
      val changeTime = themeChanger.getNextThemeChange()
      service.scheduleThemeChange(changeTime) { themeChanger.applyTheme() }
    }
  }
}
