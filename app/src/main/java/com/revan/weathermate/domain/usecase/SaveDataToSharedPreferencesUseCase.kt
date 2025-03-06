package com.revan.weathermate.domain.usecase

import com.revan.weathermate.data.repository.SharedPreferencesRepository
import jakarta.inject.Inject

class SaveDataToSharedPreferencesUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository) {

    fun execute (key : String,value : String) {
        sharedPreferencesRepository.saveData(key,value)
    }
}