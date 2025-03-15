package com.revan.weathermate.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.revan.weathermate.domain.model.LocationItem

data class LocationDtoItem(
    @SerializedName("addresstype")
    val addresstype: String,
    @SerializedName("boundingbox")
    val boundingbox: List<String>,
    @SerializedName("class")
    val classX: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("importance")
    val importance: Double,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("osm_id")
    val osmId: Long,
    @SerializedName("place_rank")
    val placeRank: Int,
    @SerializedName("place_id")
    val placeId: Long,
    @SerializedName("type")
    val type: String
)

fun LocationDtoItem.toLocationItem(): LocationItem {
    return LocationItem(
        displayName = displayName,
        lat = lat,
        lon = lon,
        name = name
    )
}