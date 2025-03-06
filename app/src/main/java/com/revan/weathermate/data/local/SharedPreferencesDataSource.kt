package com.revan.weathermate.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import jakarta.inject.Inject

class SharedPreferencesDataSource @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun saveData(key: String, value: String) {
        sharedPreferences.edit() { putString(key, value) }
    }

    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}