package com.revan.weathermate.domain.usecase

import com.revan.weathermate.data.remote.dto.LocationDto
import com.revan.weathermate.data.remote.dto.toLocation
import com.revan.weathermate.data.remote.dto.toLocationItem
import com.revan.weathermate.domain.model.Location
import com.revan.weathermate.domain.model.LocationItem
import com.revan.weathermate.domain.repository.LocationRepository
import com.revan.weathermate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val repository : LocationRepository)  {
     fun execute (city : String) : Flow<Resource<Location>> = flow {
        emit(Resource.Loading())
        try {
            val locations= repository.getLocations(city)
            if (locations.isEmpty()){
                emit(Resource.Error("No location found"))
            }else{
                emit(Resource.Success(locations.toLocation()))
            }
        }catch (e : Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}