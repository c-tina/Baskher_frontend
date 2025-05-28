package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.example.baskher_frontend.data.api.models.JugadoraResponse
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.theme.PurpleGrey40

@Composable
fun BusquedaJugadora(
    label: String,
    onJugadoraSeleccionada: (JugadoraResponse) -> Unit,
    jugadoras: List<JugadoraResponse>,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var selectedJugadora by remember { mutableStateOf<JugadoraResponse?>(null) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val filteredJugadoras by remember(searchText, jugadoras, selectedJugadora) {
        derivedStateOf {
            if (searchText.length >= 2) {
                jugadoras.filter {
                    it.nombre.contains(searchText, ignoreCase = true) &&
                            it != selectedJugadora
                }.take(5)
            } else emptyList()
        }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                if (it.isEmpty() || !it.equals(selectedJugadora?.nombre, ignoreCase = true)) {
                    selectedJugadora = null
                }
            },
            label = { Text(label, color = PurpleDark) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = PurpleDark,
                unfocusedIndicatorColor = PurpleDark,
                focusedLabelColor = PurpleDark,
                unfocusedLabelColor = PurpleDark
            ),
            singleLine = true
        )

        if (filteredJugadoras.isNotEmpty() && searchText.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PurpleGrey40)
            ) {
                filteredJugadoras.forEach { jugadora ->
                    DropdownMenuItem(
                        text = { Text(jugadora.nombre, color = Color.White) },
                        onClick = {
                            searchText = jugadora.nombre
                            selectedJugadora = jugadora
                            onJugadoraSeleccionada(jugadora)
                            focusManager.clearFocus()
                        }
                    )
                }
            }
        }
    }
}


