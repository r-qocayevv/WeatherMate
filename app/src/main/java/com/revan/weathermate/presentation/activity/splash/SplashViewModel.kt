package com.revan.weathermate.presentation.activity.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revan.weathermate.domain.usecase.GetDataFromSharedPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application,
    private val getDataFromSharedPreferencesUseCase: GetDataFromSharedPreferencesUseCase)
    : AndroidViewModel(application)  {

    private var _isFinishedGuide : MutableLiveData<String> = MutableLiveData()
    val isFinishedGuide : LiveData<String> = _isFinishedGuide

    fun getData (key : String) {
        _isFinishedGuide.value = getDataFromSharedPreferencesUseCase.execute(key)
    }

}