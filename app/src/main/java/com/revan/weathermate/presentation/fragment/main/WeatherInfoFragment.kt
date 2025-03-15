package com.revan.weathermate.presentation.fragment.main

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.revan.weathermate.R
import com.revan.weathermate.databinding.FragmentWeatherInfoBinding
import com.revan.weathermate.domain.model.Current
import com.revan.weathermate.domain.model.CurrentUnits
import com.revan.weathermate.domain.model.Daily
import com.revan.weathermate.domain.model.Hourly
import com.revan.weathermate.domain.model.WeatherForecast
import com.revan.weathermate.presentation.fragment.daily.DailyWeatherForecastAdapter
import com.revan.weathermate.presentation.fragment.hourly.HourlyWeatherForecastAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@AndroidEntryPoint
@SuppressLint("SetTextI18n")
class WeatherInfoFragment : Fragment() {
    private var _binding: FragmentWeatherInfoBinding? = null
    val binding get() = _binding!!
    private val viewModel: WeatherInfoViewModel by viewModels()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherInfoBinding.inflate(layoutInflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showStatusBarAndNavigationBar()
        getUserLocation(getWeatherForecast = { longitude, latitude ->
            viewModel.getWeatherForecast(latitude, longitude)
        })
        navigate()

        viewModel.weatherForecastData.observe(viewLifecycleOwner) { data ->
            data?.let {
                setDateDataToView(it)
                setWeatherForecast(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            setLoadingVisibility(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            setErrorMessage(it)
        }
    }

    private fun navigate() {
        binding.addLocationButton.setOnClickListener {
            findNavController().navigate(R.id.action_weatherInfoFragment_to_searchLocationFragment)
        }
    }

    private fun showStatusBarAndNavigationBar() {
        ViewCompat.setOnApplyWindowInsetsListener(requireActivity().findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation(getWeatherForecast: (longitude: Double, latitude: Double) -> Unit) {
        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude

                    getWeatherForecast(longitude, latitude)

                    getAddressFromLocation(latitude, longitude)
                }
            }
    }

    private fun setWeatherForecast(weatherForecast: WeatherForecast) {
        binding.errorMessageText.visibility = View.GONE
        setCurrentWeatherForecast(weatherForecast.current, weatherForecast.currentUnits)
        setHourlyWeatherForecastAdapter(weatherForecast.hourly)
        setDailyWeatherForecastAdapter(weatherForecast.daily)
    }

    private fun setDateDataToView(data: WeatherForecast) {
        val localDate = LocalDate.parse(data.current.time, formatter)
        val dayOfWeek =
            localDate.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }
        val dayOfMonthAndMonth = localDate.month.getDisplayName(
            TextStyle.SHORT,
            Locale.ENGLISH
        ) + " " + localDate.dayOfMonth
        binding.apply {
            dayOfWeekText.text = dayOfWeek
            localDayText.text = dayOfMonthAndMonth
            dayOfWeekAndCurrentDayText.text = dayOfWeek + " | " + dayOfMonthAndMonth
        }
    }

    private fun setDailyWeatherForecastAdapter(dailyWeatherForecast: Daily) {
        binding.dailyRV.adapter = DailyWeatherForecastAdapter(dailyWeatherForecast)
        binding.dailyRV.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setCurrentWeatherForecast(
        currentWeatherForecast: Current,
        currentWeatherForecastUnits: CurrentUnits
    ) {
        binding.apply {
            currentTempretureText.text = "${currentWeatherForecast.temperature2m.toInt()}"
            windSpeed.text =
                "${currentWeatherForecast.windSpeed10m}" + currentWeatherForecastUnits.windSpeed10m
            pressure.text =
                "${currentWeatherForecast.pressureMsl.toInt()} " + currentWeatherForecastUnits.pressureMsl
            humidity.text =
                "${currentWeatherForecast.relativeHumidity2m}" + currentWeatherForecastUnits.relativeHumidity2m
            chanceOfRain.text = "${currentWeatherForecast.rain}%"
        }

    }

    private fun setHourlyWeatherForecastAdapter(hourlyWeatherForecast: Hourly) {
        val localTime = LocalTime.now().hour
        binding.hourlyRV.adapter = HourlyWeatherForecastAdapter(hourlyWeatherForecast)
        binding.hourlyRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.hourlyRV.scrollToPosition(localTime)
    }

    private fun getAddressFromLocation(lat: Double, lon: Double) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            if (addresses!!.isNotEmpty()) {
                val address = addresses[0].locality ?: addresses[0].subAdminArea
                binding.locationText.text = address
            } else {
                binding.locationText.text = "Address not found"
            }
        } catch (e: Exception) {
            binding.locationText.text = "Address not found"
        }
    }

    private fun setErrorMessage(errorMessage: String) {
        binding.progressBar.visibility = View.GONE
        binding.errorMessageText.visibility = View.VISIBLE
        binding.errorMessageText.text = errorMessage
    }

    private fun setLoadingVisibility(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.mainView.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}


