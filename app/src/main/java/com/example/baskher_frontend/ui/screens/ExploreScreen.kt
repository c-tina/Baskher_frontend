package com.example.baskher_frontend.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baskher_frontend.ui.components.GradientButtons
import com.example.baskher_frontend.ui.components.JugadorasList
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: JugadoraViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("Ordenar de A-Z") }

    val jugadoras by viewModel.jugadoras.collectAsState()

    // Efecto para cargar las jugadoras al inicio
    LaunchedEffect(Unit) {
        viewModel.fetchJugadoras()
    }

    val filteredJugadoras = jugadoras
        .filter { it.nombre.contains(searchQuery, ignoreCase = true) }
        .let {
            when (selectedOption) {
                "Ordenar por equipo" -> it.sortedBy { it.equipo }
                else -> it.sortedBy { it.nombre }
            }
        }
    Box(
        modifier = Modifier
            .background(PurpleBack)
            .fillMaxWidth()
            .fillMaxHeight()
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 110.dp, bottom = 16.dp)
            .background(PurpleBack),
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            placeholder = { Text("Buscar jugadora...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            singleLine = true
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            GradientButtons(selectedOption = selectedOption, onOptionSelected = { selectedOption = it })
        }

        JugadorasList(
            jugadoras = filteredJugadoras,
            navigateToDetail = navigateToDetail
        )
    }
}