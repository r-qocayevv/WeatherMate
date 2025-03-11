package com.revan.weathermate.data.remote.dto.daily


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.DailyUnits

data class DailyUnitsDto(
    @SerializedName("precipitation_probability_max")
    val precipitationProbabilityMax: String,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: String,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: String,
    @SerializedName("time")
    val time: String
)

fun DailyUnitsDto.toDailyUnits(): DailyUnits {
    return DailyUnits(
        precipitationProbabilityMax,
        temperature2mMax,
        temperature2mMin,
        time
    )
}