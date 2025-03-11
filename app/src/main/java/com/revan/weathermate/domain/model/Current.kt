package com.revan.weathermate.domain.model



data class Current(
    val isDay: Int,
    val pressureMsl: Double,
    val rain: Int,
    val relativeHumidity2m: Int,
    val temperature2m: Double,
    val time: String,
    val windSpeed10m: Double
)
