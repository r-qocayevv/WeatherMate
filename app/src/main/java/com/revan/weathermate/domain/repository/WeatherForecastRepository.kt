package com.revan.weathermate.domain.repository

import com.revan.weathermate.data.remote.dto.WeatherForecastDto

interface WeatherForecastRepository {

    suspend fun getWeatherForecast (lat : Double, long : Double) : WeatherForecastDto

}