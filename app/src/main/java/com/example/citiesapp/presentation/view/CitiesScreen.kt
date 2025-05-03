package com.example.citiesapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.citiesapp.presentation.viewmodel.CityViewModel


@Composable
fun CityScreen(
    modifier: Modifier = Modifier,
    viewModel: CityViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var query by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.loadMoreCities()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        SearchBar(query) { query = it }

        when (state) {
            is CityListViewState.Loading -> LoadingBar()
            is CityListViewState.Success -> {
                val cities = (state as CityListViewState.Success).cities
                val filteredCities = if (query.isBlank()) cities else {
                    cities.filter {
                        it.name.startsWith(query, ignoreCase = true) ||
                                it.country.startsWith(query, ignoreCase = true)
                    }
                }

                if (filteredCities.isEmpty()) {
                    EmptyStateScreen()
                } else {
                    CityList(filteredCities, listState)
                }
            }
            is CityListViewState.Error -> EmptyStateScreen()
            else -> EmptyStateScreen()
        }
    }
}


@Composable
fun LoadingBar(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun EmptyStateScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No cities available", color = Color.Gray)
    }
}
