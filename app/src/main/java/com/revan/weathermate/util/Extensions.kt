package com.revan.weathermate.util

import android.widget.ImageView
import android.widget.TextView
import com.revan.weathermate.R

fun TextView.weatherCodeToText (weatherCode: Int) {
    text = when (weatherCode){
        0 -> "Clear sky"
        1,2,3 -> "Partly cloudy"
        45,48 -> "Fog"
        51,53,55 -> "Drizzle"
        56,57 -> "Freezing Drizzle"
        61,63,65 -> "Rain"
        66,67 -> "Freezing Rain"
        71,73,75 -> "Snow"
        77 -> "Snow Grains"
        80,81,82 -> "Rain Showers"
        85,86 -> "Snow Showers"
        95 -> "Thunderstorm"
        96,99 -> "Hail"
        else -> {"Unknown"}
    }
}

fun ImageView.weatherCodeToImage  (weatherCode: Int) {
    val image = when (weatherCode){
        0 -> R.drawable.sunny
        1,2,3 -> R.drawable.partly_cloudy
        45,48 -> R.drawable.foggy
        51,53,55 -> R.drawable.rain
        56,57 -> R.drawable.rain
        61,63,65 -> R.drawable.rainy
        66,67 -> R.drawable.snow
        71,73,75 -> R.drawable.snow
        77 -> R.drawable.snow
        80,81,82 -> R.drawable.rain
        85,86 -> R.drawable.snow
        95 -> R.drawable.thunderstorm
        96,99 -> R.drawable.thunderstorm
        else -> R.drawable.partly_cloudy_with_sun
    }
    setImageResource(image)
}

fun ImageView.weatherCodeToIcon  (weatherCode: Int) {
    val image = when (weatherCode){
        0 -> R.drawable.icon_sunny
        1,2,3 -> R.drawable.icon_partly_cloudy_with_sun
        45,48 -> R.drawable.icon_foggy
        51,53,55 -> R.drawable.icon_weather_rain
        56,57 -> R.drawable.icon_weather_rain
        61,63,65 -> R.drawable.icon_weather_rain
        66,67 -> R.drawable.icon_snowy
        71,73,75 -> R.drawable.icon_snowy
        77 -> R.drawable.icon_snowy
        80,81,82 -> R.drawable.icon_sunny_rainy
        85,86 -> R.drawable.icon_snowy
        95 -> R.drawable.icon_thunderstorm
        96,99 -> R.drawable.icon_thunderstorm
        else -> R.drawable.icon_partly_cloudy_with_sun
    }
    setImageResource(image)
}