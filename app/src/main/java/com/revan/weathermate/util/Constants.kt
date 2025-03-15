package com.revan.weathermate.util

object Constants {

    const val WEATHER_FORECAST_BASE_URL = "https://api.open-meteo.com/"
    const val LOCATION_BASE_URL = "https://nominatim.openstreetmap.org/"
    const val FORMAT = "json"
    const val SELECTED_CURRENT_WEATHER_PARAMETERS = "temperature_2m,relative_humidity_2m,is_day,rain,pressure_msl,wind_speed_10m"
    const val SELECTED_DAILY_WEATHER_PARAMETERS = "temperature_2m_max,temperature_2m_min,precipitation_probability_max"
    const val SELECTED_HOURLY_WEATHER_PARAMETERS = "temperature_2m,precipitation_probability,precipitation,rain"
}