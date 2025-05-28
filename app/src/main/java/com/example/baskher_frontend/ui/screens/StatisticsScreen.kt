package com.example.baskher_frontend.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baskher_frontend.R
import com.example.baskher_frontend.ui.components.JugadoraListStatistics
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: JugadoraViewModel,
    navigateToDetail: (Int) -> Unit
) {
    val opciones = listOf(
        "Puntos Totales",
        "Asistencias",
        "Rebotes Totales",
        "% Tiros de 2",
        "% Tiros de 3",
        "% Tiros Libres"
    )

    var opcionSeleccionada by remember { mutableStateOf(opciones[0]) }
    var expanded by remember { mutableStateOf(false) }

    val jugadoras by viewModel.jugadoras.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchJugadoras()
    }

    val jugadorasOrdenadas = remember(opcionSeleccionada, jugadoras) {
        when (opcionSeleccionada) {
            "Puntos Totales" -> jugadoras.sortedByDescending { it.puntos_totales }
            "Asistencias" -> jugadoras.sortedByDescending { it.asistencias }
            "Rebotes Totales" -> jugadoras.sortedByDescending { it.rebotes_totales }
            "% Tiros de 2" -> jugadoras.sortedByDescending { it.porcentaje_t2 }
            "% Tiros de 3" -> jugadoras.sortedByDescending { it.porcentaje_t3 }
            "% Tiros Libres" -> jugadoras.sortedByDescending { it.porcentaje_tl }
            else -> jugadoras
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 110.dp, bottom = 16.dp)
            .background(PurpleBack),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.liga_footer),
            contentDescription = "Logo LF Endesa",
            modifier = Modifier
                .size(180.dp)
        )

        Text(
            text = "Liga Femenina Endesa",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.50f),
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    value = opcionSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        color = PurpleDark,
                        fontSize = 18.sp
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(PurpleBack)
                ) {
                    opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion, color = Color.White) },
                            onClick = {
                                opcionSeleccionada = opcion
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Nombre",
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 75.dp),
                color = PurpleDark,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Equipo",
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 55.dp),
                color = PurpleDark,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Total",
                modifier = Modifier.weight(1f),
                color = PurpleDark,
                fontWeight = FontWeight.Bold
            )
        }

        HorizontalDivider(
            color = PurpleDark,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 4.dp)
        )

        JugadoraListStatistics(
            jugadoras = jugadorasOrdenadas,
            criterioOrden = opcionSeleccionada,
            navigateToDetail = navigateToDetail
        )
    }
}