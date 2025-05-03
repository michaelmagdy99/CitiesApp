package com.example.citiesapp.presentation.viewmodel

import CityListViewState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citiesapp.domain.usecase.GetCitiesUseCase
import com.example.citiesapp.presentation.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
): ViewModel() {

    private val _state = MutableStateFlow<CityListViewState>(CityListViewState.Loading)
    val state: StateFlow<CityListViewState> = _state

    fun loadMoreCities() {
        viewModelScope.launch {
            try {
                val result = getCitiesUseCase.invoke()
                if (result.isNotEmpty()) {
                    _state.value = CityListViewState.Success(result.map { it.toUiModel() }.toList())
                }
            } catch (e: Exception) {
                _state.value = CityListViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
