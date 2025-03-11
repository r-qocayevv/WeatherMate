package com.revan.weathermate.domain.usecase

import com.revan.weathermate.data.repository.SharedPreferencesRepositoryImpl
import javax.inject.Inject

class GetDataFromSharedPreferencesUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepositoryImpl) {

    fun execute (key: String): String? {
        return sharedPreferencesRepository.getData(key)
    }
}