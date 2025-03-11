package com.revan.weathermate.presentation.fragment.guide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.revan.weathermate.domain.usecase.SaveDataToSharedPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    application: Application,
    private val useCase: SaveDataToSharedPreferencesUseCase
) : AndroidViewModel(application) {

    fun saveData(key : String,value : String){
        useCase.execute(key,value)
    }
}