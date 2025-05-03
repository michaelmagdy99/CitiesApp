package com.example.citiesapp.presentation.mapper

import com.example.citiesapp.domain.entities.CityDomainModel
import com.example.citiesapp.presentation.model.CityUiModel

fun CityDomainModel.toUiModel(): CityUiModel {
    return CityUiModel(
        name = name,
        country = country,
        latitude = coordinates.lat,
        longitude = coordinates.lon,
    )
}

