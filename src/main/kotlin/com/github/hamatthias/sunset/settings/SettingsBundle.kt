package com.github.hamatthias.sunset.settings

import com.intellij.DynamicBundle
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.util.function.Supplier

object SettingsBundle {

  @NonNls
  private const val BUNDLE = "settings.labels"

  private val instance = DynamicBundle(SettingsBundle::class.java, BUNDLE)

  @JvmStatic
  fun setting(key: @PropertyKey(resourceBundle = BUNDLE) String, vararg params: Any) : @Nls String {
      return instance.getMessage(key, *params)
  }

  @JvmStatic
  fun lazySetting(@PropertyKey(resourceBundle = BUNDLE) key : String, vararg params: Any) : Supplier<@Nls String> {
    return instance.getLazyMessage(key, *params)
  }
}