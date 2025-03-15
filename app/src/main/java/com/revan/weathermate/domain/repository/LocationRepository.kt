package com.revan.weathermate.domain.repository

import com.revan.weathermate.data.remote.dto.LocationDto

interface LocationRepository {
    suspend fun getLocations (city : String)  : LocationDto
}