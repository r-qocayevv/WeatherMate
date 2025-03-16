package com.revan.weathermate.data.remote.dto.hourly


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.HourlyUnits

data class HourlyUnitsDto(
    @SerializedName("precipitation")
    val precipitation: String,
    @SerializedName("precipitation_probability")
    val precipitationProbability: String,
    @SerializedName("rain")
    val rain: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String
)

fun HourlyUnitsDto.toHourlyUnits(): HourlyUnits {
    return HourlyUnits(
        precipitation,
        precipitationProbability,
        rain,
        temperature2m,
        time
    )
}