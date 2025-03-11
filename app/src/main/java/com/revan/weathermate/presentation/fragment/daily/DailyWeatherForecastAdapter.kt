package com.revan.weathermate.presentation.fragment.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revan.weathermate.databinding.DailyForecastRowLayoutBinding
import com.revan.weathermate.domain.model.Daily
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DailyWeatherForecastAdapter(val dailyWeatherForecast : Daily) : RecyclerView.Adapter<DailyWeatherForecastAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : DailyForecastRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(DailyForecastRowLayoutBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding  = holder.binding

        val currentDayOfWeek = LocalDate
            .parse(dailyWeatherForecast.time[position],
            DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        binding.dayOfWeekText.text =
            currentDayOfWeek.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        binding.minimumAndMaximumTemperatureText.text =
            "${dailyWeatherForecast.temperature2mMin[position].toInt()}°/${dailyWeatherForecast.temperature2mMax[position].toInt()}°"
        binding.chanceOfRainText.text =
            dailyWeatherForecast.precipitationProbabilityMax[position].toInt().toString() + "% rain"
    }

    override fun getItemCount(): Int {
        return 7
    }

}