package com.example.baskher_frontend.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baskher_frontend.data.api.models.JugadoraResponse
import com.example.baskher_frontend.ui.components.BusquedaJugadora
import com.example.baskher_frontend.ui.components.JugadoraColumn
import com.example.baskher_frontend.ui.components.RadarChartView
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel

@Composable
fun CompareScreen(
    modifier: Modifier = Modifier,
    viewModel: JugadoraViewModel
) {
    val jugadoras by viewModel.jugadoras.collectAsState()

    var jugadora1 by remember { mutableStateOf<JugadoraResponse?>(null) }
    var jugadora2 by remember { mutableStateOf<JugadoraResponse?>(null) }


    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .background(PurpleBack)
            .fillMaxWidth()
            .fillMaxHeight()
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 130.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(scrollState)
    ) {
        // Campos de búsqueda
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                BusquedaJugadora(
                    label = "Buscar Jugadora",
                    onJugadoraSeleccionada = { jugadora1 = it },
                    jugadoras = jugadoras
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                BusquedaJugadora(
                    label = "Buscar Jugadora",
                    onJugadoraSeleccionada = { jugadora2 = it },
                    jugadoras = jugadoras
                )
            }
        }

        if (jugadora1 != null && jugadora2 != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                JugadoraColumn(jugadora1!!, modifier = Modifier.weight(1f))
                JugadoraColumn(jugadora2!!, modifier = Modifier.weight(1f))
            }

            val labels = listOf(
                "Puntos totales",
                "Asistencias",
                "Rebotes totales",
                "% Tiros de 2",
                "% Tiros de 3",
                "% Tiros Libres"
            )

            val stats1 = listOf(
                normalize(jugadora1!!.puntos_totales.toFloat(), 0f, 620f),
                normalize(jugadora1!!.asistencias.toFloat(), 0f, 200f),
                normalize(jugadora1!!.rebotes_totales.toFloat(), 0f, 305f),
                jugadora1!!.porcentaje_t2,
                jugadora1!!.porcentaje_t3,
                jugadora1!!.porcentaje_tl
            )
            val stats2 = listOf(
                normalize(jugadora1!!.puntos_totales.toFloat(), 0f, 500f),
                normalize(jugadora1!!.asistencias.toFloat(), 0f, 100f),
                normalize(jugadora1!!.rebotes_totales.toFloat(), 0f, 200f),
                jugadora2!!.porcentaje_t2,
                jugadora2!!.porcentaje_t3,
                jugadora2!!.porcentaje_tl
            )

            // Radar Chart centrado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                RadarChartView(stats1, stats2, labels)
            }
        } else {
            Text(
                text = "Selecciona dos jugadoras para comparar.",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun normalize(value: Float, min: Float, max: Float): Float {
    return ((value - min) / (max - min)) * 100f
}

