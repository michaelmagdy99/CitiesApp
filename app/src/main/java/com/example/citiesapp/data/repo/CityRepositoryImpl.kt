package com.example.citiesapp.data.repo

import android.content.Context
import com.example.citiesapp.data.mapper.toDomain
import com.example.citiesapp.data.model.CityDataModel
import com.example.citiesapp.domain.entities.CityDomainModel
import com.example.citiesapp.domain.repo.ICityRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val context: Context
) : ICityRepository {

    override suspend fun getCities(): List<CityDomainModel> {
        return loadCitiesFromJson().sortedBy { it.name.uppercase() }
    }

    private suspend fun loadCitiesFromJson(): List<CityDomainModel> = withContext(Dispatchers.IO) {
        val jsonString = context.assets.open("cities.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<CityDataModel>>() {}.type
        val cityDataModelList: List<CityDataModel> = Gson().fromJson(jsonString, type)

        return@withContext cityDataModelList.map { it.toDomain() }
    }
}
