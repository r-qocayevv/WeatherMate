package com.revan.weathermate.presentation.fragment.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revan.weathermate.databinding.HourlyForecastColumnLayoutBinding
import com.revan.weathermate.domain.model.Hourly
import com.revan.weathermate.util.weatherCodeToIcon
import java.time.LocalTime

class HourlyWeatherForecastAdapter(val hourlyWeatherForecast: Hourly) :
    RecyclerView.Adapter<HourlyWeatherForecastAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: HourlyForecastColumnLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(HourlyForecastColumnLayoutBinding.inflate(layoutInflater, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        val binding = holder.binding
        val localTime = LocalTime.now().hour
        binding.weatherImage.weatherCodeToIcon(hourlyWeatherForecast.weatherCode[position])
        binding.timeText.text =
            if (localTime == position) "Now" else position.toString().padStart(2, '0') + ":00"
        binding.minAndMaximumTemperatureText.text =
            hourlyWeatherForecast.temperature2m[position].toString()
        binding.chanceOfRainText.text =
            hourlyWeatherForecast.precipitationProbability[position].toInt().toString() + "% rain"
    }

    override fun getItemCount(): Int {
        return 24
    }
}