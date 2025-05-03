package com.example.citiesapp.domain.entities

data class CityDomainModel(
    val id: Int,
    val name: String,
    val country: String,
    val coordinates: CoordinatesDomainModel,
)

data class CoordinatesDomainModel(
    val lon: Double,
    val lat: Double
)
