package com.example.baskher_frontend.ui.components

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun RadarChartView(
    stats1: List<Float>,
    stats2: List<Float>,
    labels: List<String>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp),
        factory = { context ->
            RadarChart(context).apply {
                description.isEnabled = false
                webLineWidth = 2f
                webColor = Color.DKGRAY.hashCode()
                webColorInner = Color.LTGRAY.hashCode()

                val entries1 = stats1.mapIndexed { _, value -> RadarEntry(value) }
                val entries2 = stats2.mapIndexed { _, value -> RadarEntry(value) }

                val dataSet1 = RadarDataSet(entries1, "Jugadora 1").apply {
                    color = Color.MAGENTA
                    fillColor = Color.MAGENTA
                    setDrawFilled(true)
                }

                val dataSet2 = RadarDataSet(entries2, "Jugadora 2").apply {
                    color = Color.BLUE
                    fillColor = Color.BLUE
                    setDrawFilled(true)
                }

                data = RadarData(listOf(dataSet1, dataSet2))
                xAxis.apply {
                    textSize = 12f
                    valueFormatter = IndexAxisValueFormatter(labels)
                }
                yAxis.apply {
                    setDrawLabels(false)
                }
                invalidate()
            }
        }
    )
}
