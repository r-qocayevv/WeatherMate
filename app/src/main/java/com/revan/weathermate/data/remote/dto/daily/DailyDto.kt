package com.revan.weathermate.data.remote.dto.daily


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.Daily

data class DailyDto(
    @SerializedName("precipitation_probability_max")
    val precipitationProbabilityMax: List<Double>,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)

fun DailyDto.toDaily(): Daily {
    return Daily(
        precipitationProbabilityMax,
        temperature2mMax,
        temperature2mMin,
        time,
        weatherCode
    )
}