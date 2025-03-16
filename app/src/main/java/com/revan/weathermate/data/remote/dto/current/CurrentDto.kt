package com.revan.weathermate.data.remote.dto.current


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.Current

data class CurrentDto(
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("pressure_msl")
    val pressureMsl: Double,
    @SerializedName("rain")
    val rain: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int,
    @SerializedName("temperature_2m")
    val temperature2m: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: Double
)

fun CurrentDto.toCurrent(): Current {
    return Current(
        isDay,
        pressureMsl,
        rain,
        relativeHumidity2m,
        temperature2m,
        time,
        windSpeed10m,
        weatherCode
    )
}