package com.revan.weathermate.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.data.remote.dto.current.CurrentDto
import com.revan.weathermate.data.remote.dto.current.CurrentUnitsDto
import com.revan.weathermate.data.remote.dto.current.toCurrent
import com.revan.weathermate.data.remote.dto.current.toCurrentUnits
import com.revan.weathermate.data.remote.dto.daily.DailyDto
import com.revan.weathermate.data.remote.dto.daily.DailyUnitsDto
import com.revan.weathermate.data.remote.dto.daily.toDaily
import com.revan.weathermate.data.remote.dto.daily.toDailyUnits
import com.revan.weathermate.data.remote.dto.hourly.HourlyDto
import com.revan.weathermate.data.remote.dto.hourly.HourlyUnitsDto
import com.revan.weathermate.data.remote.dto.hourly.toHourly
import com.revan.weathermate.data.remote.dto.hourly.toHourlyUnits
import com.revan.weathermate.domain.model.WeatherForecast

data class WeatherForecastDto(
    @SerializedName("current")
    val current: CurrentDto,
    @SerializedName("current_units")
    val currentUnits: CurrentUnitsDto,
    @SerializedName("daily")
    val daily: DailyDto,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnitsDto,
    @SerializedName("elevation")
    val elevation: Int,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    @SerializedName("hourly")
    val hourly: HourlyDto,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnitsDto,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)


fun WeatherForecastDto.toWeatherForecast(): WeatherForecast {
    return WeatherForecast(
        current.toCurrent(),
        currentUnits.toCurrentUnits(),
        daily.toDaily(),
        dailyUnits.toDailyUnits(),
        elevation,
        generationTimeMs,
        hourly.toHourly(),
        hourlyUnits.toHourlyUnits(),
        latitude,
        longitude,
        timezone,
        timezoneAbbreviation,
        utcOffsetSeconds
    )

}