package com.example.citiesapp.di

import android.content.Context
import com.example.citiesapp.data.repo.CityRepositoryImpl
import com.example.citiesapp.domain.repo.ICityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCityRepository(
        @ApplicationContext context: Context
    ): ICityRepository {
        return CityRepositoryImpl(context)
    }
}
