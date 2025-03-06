package com.revan.weathermate.domain.usecase

import com.revan.weathermate.data.repository.SharedPreferencesRepository
import javax.inject.Inject

class GetDataFromSharedPreferencesUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository) {

    fun execute (key: String): String? {
        return sharedPreferencesRepository.getData(key)
    }
}