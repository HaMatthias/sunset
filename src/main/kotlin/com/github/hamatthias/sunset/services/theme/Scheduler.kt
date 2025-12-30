package com.github.hamatthias.sunset.services.theme

import com.github.hamatthias.sunset.notification.NotificationGatherer
import com.intellij.notification.Notifications
import com.intellij.openapi.application.EDT
import com.intellij.openapi.components.Service
import com.intellij.util.Time
import kotlinx.coroutines.*
import java.time.Duration
import java.time.LocalDateTime

@Service
class Scheduler(
  private val cs: CoroutineScope
) {

  // There should be only one Job at a time
  private lateinit var themeChangeJob : Job

  fun scheduleThemeChange(executionTime: LocalDateTime, themeChangeTask: () -> Unit) {

    if (::themeChangeJob.isInitialized) {
      themeChangeJob.cancel()
    }

    val notification = NotificationGatherer.getThemeChangeNotification()
    val now = LocalDateTime.now()
    if (executionTime.isAfter(now)) {
      val delay = Duration.between(now, executionTime).toMillis()
      themeChangeJob = cs.launch {
        withContext(Dispatchers.EDT) {
          if (delay - Time.MINUTE > 0) {
            delay(delay - Time.MINUTE.toLong())
          }
          Notifications.Bus.notify(notification)
          delay(Time.MINUTE.toLong())
          themeChangeTask()
        }
      }
    }
  }

  fun cancelThemeChange() {
    themeChangeJob.cancel()
  }
}