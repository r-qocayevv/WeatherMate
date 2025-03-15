package com.revan.weathermate.presentation.fragment.searchLocation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.revan.weathermate.domain.model.Location
import com.revan.weathermate.domain.usecase.GetLocationsUseCase
import com.revan.weathermate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(
    val getLocationsUseCase: GetLocationsUseCase,
    application: Application
) : AndroidViewModel(application) {

    private var job : Job? = null

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    private var _location : MutableLiveData<Location> = MutableLiveData()
    val location : MutableLiveData<Location> get() = _location

    fun getLocations(city : String) {
        job?.cancel()
        job = getLocationsUseCase.execute(city).onEach {
            when(it) {
                is Resource.Success -> {
                    _isLoading.value = false
                    it.data?.let { data ->
                        _location.value = data
                    }
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _error.value = it.message ?: "An unexpected error occurred"
                }
            }

        }.launchIn(viewModelScope)

    }

}