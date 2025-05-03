package com.example.citiesapp.presentation.view

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.citiesapp.presentation.model.CityUiModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityList(
    cities: List<CityUiModel>,
    listState: LazyListState
) {
    val context = LocalContext.current
    val groupedCities = cities.groupBy { it.name.first().uppercaseChar().takeIf { it in 'A'..'Z' } ?: '#' }

    val headerIndexMap = remember(groupedCities) {
        val map = mutableMapOf<Char, Int>()
        var index = 0
        groupedCities.forEach { (letter, citiesInGroup) ->
            map[letter] = index
            index += 1 + citiesInGroup.size
        }
        map
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 8.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            groupedCities.forEach { (letter, citiesInGroup) ->
                stickyHeader {
                    CitySectionHeader(letter = letter.uppercase())
                }
                items(citiesInGroup) { city ->
                    CityItem(city = city) { selectedCity ->
                        val uri = "geo:${selectedCity.latitude},${selectedCity.longitude}?q=${selectedCity.name}".toUri()
                        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                            setPackage("com.google.android.apps.maps")
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        AlphabetIndex(headerIndexMap, listState = listState)
    }
}




@Composable
fun CitySectionHeader(letter: String) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Text(
            text = letter,
            color = Color.LightGray,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp),
        )
    }
}


@Composable
fun AlphabetIndex(
    headerIndexMap: Map<Char, Int>,
    listState: LazyListState,
) {
    val alphabet = ('A'..'Z') + '#'
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        alphabet.forEach { letter ->
            Text(
                text = letter.toString(),
                modifier = Modifier
                    .clickable {
                        headerIndexMap[letter]?.let { index ->
                            scope.launch { listState.animateScrollToItem(index) }
                        }
                    }
                    .padding(7.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}