package com.example.citiesapp.domain.repo

import com.example.citiesapp.domain.entities.CityDomainModel

interface ICityRepository {
    suspend fun getCities(): List<CityDomainModel>
}