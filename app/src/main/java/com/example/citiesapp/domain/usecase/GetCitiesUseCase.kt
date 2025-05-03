package com.example.citiesapp.domain.usecase

import com.example.citiesapp.domain.entities.CityDomainModel
import com.example.citiesapp.domain.repo.ICityRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ICityRepository
) {
    suspend operator fun invoke(): List<CityDomainModel> {
        return repository.getCities()
    }
}
