package com.example.citiesapp.data.model

data class CityDataModel(
    val country: String,
    val name: String,
    val _id: Int,
    val coord: CoordDataModel
)

data class CoordDataModel(
    val lon: Double,
    val lat: Double
)
