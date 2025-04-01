package com.github.hamatthias.sunset.services.theme

import com.intellij.openapi.application.EDT
import com.intellij.openapi.components.Service
import kotlinx.coroutines.*
import java.time.Duration
import java.time.LocalTime

/**
 * Launches a [CoroutineScope] to change a theme at a specific time.
 *
 * fdsjgsghaslghsgfdf
 * dfdfdsffd
 */
@Service
class ThemeChangerSchedulingService(
  private val cs: CoroutineScope
) {

  // There should be only one Job at a time
  private lateinit var themeChangeJob : Job

  fun scheduleThemeChange(executionTime: LocalTime, themeChangeTask: () -> Unit) {

    val now = LocalTime.now()
    if (executionTime.isAfter(now)) {
      val delay = Duration.between(now, executionTime).toMillis()
      themeChangeJob = cs.launch {
        withContext(Dispatchers.EDT) {
          delay(delay)
          themeChangeTask()
        }
      }
    }
  }
}