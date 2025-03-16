package com.revan.weathermate.data.remote.dto.hourly


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.Hourly

data class HourlyDto(
    @SerializedName("precipitation")
    val precipitation: List<Double>,
    @SerializedName("precipitation_probability")
    val precipitationProbability: List<Double>,
    @SerializedName("rain")
    val rain: List<Double>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)

fun HourlyDto.toHourly(): Hourly {
    return Hourly(
        precipitationProbability,
        temperature2m,
        weatherCode
    )
}