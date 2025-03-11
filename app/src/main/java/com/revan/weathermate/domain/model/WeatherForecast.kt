package com.revan.weathermate.domain.model



data class WeatherForecast(
    val current: Current,
    val currentUnits: CurrentUnits,
    val daily: Daily,
    val dailyUnits: DailyUnits,
    val elevation: Int,
    val generationTimeMs: Double,
    val hourly: Hourly,
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int
)