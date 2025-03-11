package com.revan.weathermate.domain.usecase

import com.revan.weathermate.data.remote.dto.toWeatherForecast
import com.revan.weathermate.domain.model.WeatherForecast
import com.revan.weathermate.domain.repository.WeatherForecastRepository
import com.revan.weathermate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(val repository : WeatherForecastRepository) {

     fun execute (lat : Double, long : Double) : Flow<Resource<WeatherForecast>> = flow{
        emit(Resource.Loading())
        try {
            val weatherForecast = repository.getWeatherForecast(lat,long).toWeatherForecast()
            emit(Resource.Success(weatherForecast))
        }catch (e : Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}