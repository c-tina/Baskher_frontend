package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.baskher_frontend.data.api.models.JugadoraResponse
import com.example.baskher_frontend.ui.theme.PurpleDark

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun JugadoraStatistics(
    jugadora: JugadoraResponse,
    criterio: String,
    onClick: () -> Unit,
    ) {

    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(70.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = PurpleDark,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            GlideImage(
                model = jugadora.imagen,
                contentDescription = "Imagen de ${jugadora.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .padding(end = 16.dp)
                    .clip(CircleShape)

            )
            Text(
                text = formatearNombre(jugadora.nombre),
                modifier = Modifier
                    .weight(5f)
                    .padding(start = 6.dp),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = obtenerIniciales(jugadora.equipo),
                modifier = Modifier
                    .weight(2f),
                color = Color.White,
                fontSize = 14.sp,
            )

            val valor = when (criterio) {
                "Puntos Totales" -> jugadora.puntos_totales
                "Asistencias" -> jugadora.asistencias
                "Rebotes Totales" -> jugadora.rebotes_totales
                "% Tiros de 2" -> jugadora.porcentaje_t2
                "% Tiros de 3" -> jugadora.porcentaje_t3
                "% Tiros Libres" -> jugadora.porcentaje_tl
                else -> "-"
            }

            Text(
                text = valor.toString(),
                modifier = Modifier
                    .weight(1f),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
                )
        }
    }
}
fun obtenerIniciales(equipo: String): String {
    return equipo
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.first().uppercaseChar() }
        .joinToString("")
}

fun formatearNombre(nombreCrudo: String): String {
    val partes = nombreCrudo.split(",")
    if (partes.size != 2) return nombreCrudo.lowercase().replaceFirstChar { it.uppercase() }

    val apellidos = partes[0].trim().split(" ")
    val nombres = partes[1].trim().split(" ")

    val primerApellido = apellidos.firstOrNull()?.lowercase()?.replaceFirstChar { it.uppercase() } ?: ""
    val primerNombre = nombres.firstOrNull()?.lowercase()?.replaceFirstChar { it.uppercase() } ?: ""

    return "$primerNombre $primerApellido"
}