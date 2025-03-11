package com.revan.weathermate.data.repository

import com.revan.weathermate.data.remote.WeatherForecastAPI
import com.revan.weathermate.data.remote.dto.WeatherForecastDto
import com.revan.weathermate.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(val api : WeatherForecastAPI) : WeatherForecastRepository {

    override suspend fun getWeatherForecast (lat : Double, long : Double) : WeatherForecastDto {
        return api.getWeatherForecast(lat,long)
    }
}