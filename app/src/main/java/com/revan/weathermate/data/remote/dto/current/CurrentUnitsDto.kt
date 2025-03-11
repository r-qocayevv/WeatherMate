package com.revan.weathermate.data.remote.dto.current


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.CurrentUnits

data class CurrentUnitsDto(
    @SerializedName("interval")
    val interval: String,
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("pressure_msl")
    val pressureMsl: String,
    @SerializedName("rain")
    val rain: String,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: String
)

fun CurrentUnitsDto.toCurrentUnits(): CurrentUnits {
    return CurrentUnits(
        interval,
        isDay,
        pressureMsl,
        rain,
        relativeHumidity2m,
        temperature2m,
        time,
        windSpeed10m
    )
}