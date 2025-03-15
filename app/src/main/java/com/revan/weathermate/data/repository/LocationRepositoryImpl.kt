package com.revan.weathermate.data.repository

import com.revan.weathermate.data.remote.LocationAPI
import com.revan.weathermate.data.remote.dto.LocationDto
import com.revan.weathermate.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val api : LocationAPI) : LocationRepository{
    override suspend fun getLocations(city : String): LocationDto {
        return api.getLocation(city)
    }
}