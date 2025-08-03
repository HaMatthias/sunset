package com.github.hamatthias.sunset.notification

import com.github.hamatthias.sunset.settings.SettingsBundle
import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType

object NotificationGatherer {

  fun getThemeChangeNotification(): Notification {
    val content = SettingsBundle.setting("label.notification.content")
    return NotificationGroupManager.getInstance().getNotificationGroup("Theme change").createNotification(content,
      NotificationType.INFORMATION)
  }
}