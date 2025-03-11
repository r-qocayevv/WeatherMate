package com.revan.weathermate.util

import java.time.format.DateTimeFormatter

object Constants {

    const val BASE_URL = "https://api.open-meteo.com/"
    const val SELECTED_CURRENT_WEATHER_PARAMETERS = "temperature_2m,relative_humidity_2m,is_day,rain,pressure_msl,wind_speed_10m"
    const val SELECTED_DAILY_WEATHER_PARAMETERS = "temperature_2m_max,temperature_2m_min,precipitation_probability_max"
    const val SELECTED_HOURLY_WEATHER_PARAMETERS = "temperature_2m,precipitation_probability,precipitation,rain"
}