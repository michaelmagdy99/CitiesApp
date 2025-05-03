package com.example.citiesapp.data.mapper

import com.example.citiesapp.data.model.CityDataModel
import com.example.citiesapp.data.model.CoordDataModel
import com.example.citiesapp.domain.entities.CityDomainModel
import com.example.citiesapp.domain.entities.CoordinatesDomainModel


fun CityDataModel.toDomain(): CityDomainModel {
    return CityDomainModel(
        id = _id,
        name = name,
        country = country,
        coordinates = coord.toDomain()
    )
}

fun CoordDataModel.toDomain(): CoordinatesDomainModel {
    return CoordinatesDomainModel(
        lon = lon,
        lat = lat
    )
}
