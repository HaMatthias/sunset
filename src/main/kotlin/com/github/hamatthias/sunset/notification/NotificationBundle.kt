package com.github.hamatthias.sunset.notification

import com.intellij.DynamicBundle
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

object NotificationBundle {

  @NonNls
  private const val BUNDLE = "messages.notifications"

  private val instance = DynamicBundle(NotificationBundle::class.java, BUNDLE)

  @JvmStatic
  fun notification(key: @PropertyKey(resourceBundle = BUNDLE) String, vararg params: Any) : @Nls String {
    return instance.getMessage(key, *params)
  }
}