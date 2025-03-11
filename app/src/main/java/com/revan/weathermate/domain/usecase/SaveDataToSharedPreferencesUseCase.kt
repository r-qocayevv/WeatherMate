package com.revan.weathermate.domain.usecase

import com.revan.weathermate.data.repository.SharedPreferencesRepositoryImpl
import jakarta.inject.Inject

class SaveDataToSharedPreferencesUseCase @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepositoryImpl) {

    fun execute (key : String,value : String) {
        sharedPreferencesRepository.saveData(key,value)
    }
}