package com.example.citiesapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.citiesapp.presentation.model.CityUiModel
import com.example.citiesapp.presentation.utilities.toFlagEmoji
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun CityItem(
    city: CityUiModel,
    onClick: (CityUiModel) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(city) }
            .padding(6.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        FlagEmojiImage(flagEmoji = city.country.toFlagEmoji(), size = 48.dp)
        Spacer(modifier = Modifier.width(12.dp))
        Column (
            modifier = Modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text("${city.name}, ${city.country}", style = MaterialTheme.typography.titleMedium)
            Text("Lat: ${city.latitude}, Lon: ${city.longitude}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun FlagEmojiImage(
    flagEmoji: String,
    size: Dp
) {
    Box(modifier = Modifier.size(size)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawContext.canvas.nativeCanvas.apply {
                val paint = Paint().apply {
                    textSize = size.toPx()
                    isAntiAlias = true
                }
                drawText(flagEmoji, 0f, size.toPx(), paint)
            }
        }
    }
}
