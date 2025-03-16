package com.revan.weathermate.domain.model



data class Daily(
    val precipitationProbabilityMax: List<Double>,
    val temperature2mMax: List<Double>,
    val temperature2mMin: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>
)