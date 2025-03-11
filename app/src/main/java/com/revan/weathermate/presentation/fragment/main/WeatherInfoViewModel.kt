package com.revan.weathermate.presentation.fragment.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.revan.weathermate.domain.model.WeatherForecast
import com.revan.weathermate.domain.usecase.GetWeatherForecastUseCase
import com.revan.weathermate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(
    application: Application,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase) : AndroidViewModel(application) {

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    private val _weatherForecastData = MutableLiveData<WeatherForecast>()
    val weatherForecastData: MutableLiveData<WeatherForecast> get() = _weatherForecastData

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    private var job: Job? = null

    fun getWeatherForecast (lat : Double, long : Double) {
        job?.cancel()

        job = getWeatherForecastUseCase.execute(lat,long).onEach {
            when(it) {
                is Resource.Success -> {
                    _isLoading.value = false
                    it.data?.let { data ->
                        _weatherForecastData.value = data
                    }
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }

                is Resource.Error -> {
                    _error.value = it.message ?: "An unexpected error occurred"
                }
            }
        }.launchIn(viewModelScope)
    }
}
