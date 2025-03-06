package com.revan.weathermate.data.repository

import com.revan.weathermate.data.local.SharedPreferencesDataSource
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(private val sharedPreferencesDataSource: SharedPreferencesDataSource) {
    fun saveData(key: String, value: String) {
        sharedPreferencesDataSource.saveData(key, value)
    }

    fun getData(key: String): String? {
        return sharedPreferencesDataSource.getData(key)
    }
}