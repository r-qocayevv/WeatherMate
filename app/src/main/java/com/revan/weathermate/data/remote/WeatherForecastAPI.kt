package com.revan.weathermate.data.remote

import com.revan.weathermate.data.remote.dto.WeatherForecastDto
import com.revan.weathermate.util.Constants.SELECTED_CURRENT_WEATHER_PARAMETERS
import com.revan.weathermate.util.Constants.SELECTED_DAILY_WEATHER_PARAMETERS
import com.revan.weathermate.util.Constants.SELECTED_HOURLY_WEATHER_PARAMETERS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastAPI {

    @GET("/v1/forecast")
    suspend fun getWeatherForecast (
        @Query("latitude") lat : Double,
        @Query("longitude") long : Double,
        @Query("current") selectedCurrentWeatherParameters : String = SELECTED_CURRENT_WEATHER_PARAMETERS,
        @Query("hourly") selectedHourlyWeatherParameters : String = SELECTED_HOURLY_WEATHER_PARAMETERS,
        @Query("daily") selectedDailyWeatherParameters : String = SELECTED_DAILY_WEATHER_PARAMETERS
    ) : WeatherForecastDto
}