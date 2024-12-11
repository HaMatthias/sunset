package com.github.hamatthias.sunset.services

import org.shredzone.commons.suncalc.SunTimes
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

/**
 * Calculates the zoned time of the sunrise and sunset for a specific location
 */
class SunTimesCalculator {

  /**
   * Average sun times: 6 AM (rise) and 6 PM (set) at 2024-12-11 \[UTC]
   */
  object AverageSolarChanges {
    val SUNRISE: ZonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, Month.JUNE, 11, 6, 0), ZoneOffset.UTC)
    val SUNSET: ZonedDateTime = ZonedDateTime.of(LocalDateTime.of(2024, Month.JUNE, 11, 18, 0), ZoneOffset.UTC)
  }

  /**
   * Returns the time of the sunrise of the given parameters.
   * If no time could be found, an average value is returned as follows:
   *   - 2024-12-11T06:00Z\[UTC]
   */
  fun calculateSunrise(latitude: Double = 0.0, longitude: Double = 0.0, date: Date = Date()) : ZonedDateTime {
    return SunTimes.compute().on(date).at(latitude, longitude).execute().rise ?: return AverageSolarChanges.SUNRISE
  }

  /**
   * Returns the time of the sunrise of the given parameters.
   * If no time could be found, an average value is returned as follows:
   *   2024-12-11T18:00Z\[UTC]
   */
  fun calculateSunset(latitude: Double = 0.0, longitude: Double = 0.0, date: Date = Date()) : ZonedDateTime {
    return SunTimes.compute().on(date).at(latitude, longitude).execute().set ?: return AverageSolarChanges.SUNSET
  }
}