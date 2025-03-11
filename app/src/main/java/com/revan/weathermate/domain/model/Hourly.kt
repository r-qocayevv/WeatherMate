package com.revan.weathermate.domain.model



data class Hourly(
    val precipitationProbability: List<Double>,
    val temperature2m: List<Double>,
)