package com.revan.weathermate.domain.model



data class CurrentUnits(
    val interval: String,
    val isDay: String,
    val pressureMsl: String,
    val rain: String,
    val relativeHumidity2m: String,
    val temperature2m: String,
    val time: String,
    val windSpeed10m: String,
    val weatherCode: String
)