package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.baskher_frontend.data.api.models.JugadoraResponse
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.theme.PurpleGrey40
import com.example.baskher_frontend.ui.theme.PurpleMedium

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun JugadoraColumn(jugadora: JugadoraResponse, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
        //.height(IntrinsicSize.Min) // opcional, para que ajuste alto
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(PurpleBack)
        ) {
            GlideImage(
                model = jugadora.imagen,
                contentDescription = "Imagen de ${jugadora.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.linearGradient(
                                0.0f to PurpleBack.copy(alpha = 1f),
                                0.4f to PurpleBack.copy(alpha = 0.9f),
                                0.8f to Color.Transparent,
                                start = Offset(0.0f, size.height),
                                end = Offset(0.0f, size.height * 0.4f)
                            )
                        )
                    }
            )
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.height(100.dp)) {
                Column {
                    Text(
                        text = jugadora.nombre.uppercase(),
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark,
                        fontSize = 22.sp
                    )
                    Text(
                        text = jugadora.equipo,
                        fontWeight = FontWeight.Bold,
                        color = PurpleMedium,
                        fontSize = 16.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Puntos: ${jugadora.puntos_totales}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PurpleGrey40)
                    Text("Asistencias: ${jugadora.asistencias}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PurpleGrey40)
                    Text("Rebotes: ${jugadora.rebotes_totales}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PurpleGrey40)
                    Text("T2: ${jugadora.porcentaje_t2} %", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PurpleGrey40)
                    Text("T3: ${jugadora.porcentaje_t3} %", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PurpleGrey40)
                    Text("TL: ${jugadora.porcentaje_tl} %", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PurpleGrey40)
                }
            }
        }
    }
}