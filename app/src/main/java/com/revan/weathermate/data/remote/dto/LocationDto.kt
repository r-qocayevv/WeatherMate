package com.revan.weathermate.data.remote.dto

import com.revan.weathermate.domain.model.Location


class LocationDto : ArrayList<LocationDtoItem>()

fun LocationDto.toLocation(): Location {
    val location = Location()
    for (item in this) {
        location.add(item.toLocationItem())
    }
    return location
}
