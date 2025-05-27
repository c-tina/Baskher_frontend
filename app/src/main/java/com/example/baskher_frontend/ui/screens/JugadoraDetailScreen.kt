package com.example.baskher_frontend.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun JugadoraDetailScreen(
    jugadoraId: Int,
    viewModel: JugadoraViewModel
) {
    LaunchedEffect(jugadoraId) {
        viewModel.fetchJugadoraById(jugadoraId)
    }

    val jugadora = viewModel.jugadora.observeAsState().value

    if (jugadora == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            GlideImage(
                model = jugadora.imagen,
                contentDescription = "Imagen de ${jugadora.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = jugadora.nombre,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Equipo: ${jugadora.equipo}")
            Text(text = "Puntos: ${jugadora.puntos_totales}")
            Text(text = "Asistencias: ${jugadora.asistencias}")
            Text(text = "Rebotes: ${jugadora.rebotes_totales}")
            Text(text = "T2: ${jugadora.porcentaje_t2} %")
            Text(text = "T3: ${jugadora.porcentaje_t3} %")
            Text(text = "TL: ${jugadora.porcentaje_tl} %")
        }
    }
}
