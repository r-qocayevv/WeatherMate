package com.revan.weathermate.domain.model



data class HourlyUnits(
    val precipitation: String,
    val precipitationProbability: String,
    val rain: String,
    val temperature2m: String,
    val time: String
)