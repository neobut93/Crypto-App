package com.cryptoapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun PerformanceChart(modifier: Modifier = Modifier, startPrice: Float, highestPrice: Float, lowestPrice: Float, currentPrice: Float) {
    val list: List<Float> = listOf(startPrice, highestPrice, lowestPrice, currentPrice)
    val zipList: List<Pair<Float, Float>> = list.zipWithNext()

    fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
        (value - min) / (max - min)

    Row(modifier = modifier) {
        val max = list.max()
        val min = list.min()

        val lineColor =
            if (list.last() > list.first()) Color(0xFF63B960) else Color.Red // <-- Line color is Green if its going up and Red otherwise

        for (pair in zipList) {

            val fromValuePercentage = getValuePercentageForRange(pair.first, max, min)
            val toValuePercentage = getValuePercentageForRange(pair.second, max, min)

            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onDraw = {
                    val fromPoint = Offset(x = 0f, y = size.height.times(1 - fromValuePercentage)) // <-- Use times so it works for any available space
                    val toPoint =
                        Offset(x = size.width, y = size.height.times(1 - toValuePercentage)) // <-- Also here!

                    drawLine(
                        color = lineColor,
                        start = fromPoint,
                        end = toPoint,
                        strokeWidth = 10f
                    )
                })
        }
    }
}