package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.baskher_frontend.data.api.models.JugadoraResponse

@Composable
fun JugadorasList(
    jugadoras: List<JugadoraResponse>,
    navigateToDetail: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    val columns = if (isPortrait) 3 else 6


    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        items(
            count = jugadoras.size,
            itemContent = { index: Int ->
                jugadoras[index]?.let { jugadora ->
                    JugadoraExplore(
                        jugadora = jugadora,
                        isFavorite = false,
                        onClick = { navigateToDetail(jugadora.id) }
                    )
                }
            }
        )
    }
}