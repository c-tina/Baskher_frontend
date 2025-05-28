package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baskher_frontend.data.api.models.JugadoraResponse

@Composable
fun JugadoraListStatistics(
    jugadoras: List<JugadoraResponse>,
    criterioOrden: String,
    navigateToDetail: (Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(jugadoras) { jugadora ->
            JugadoraStatistics(
                jugadora = jugadora,
                criterio = criterioOrden,
                onClick = { navigateToDetail(jugadora.id) }
            )
        }
    }
}