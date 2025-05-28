package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

import com.example.baskher_frontend.data.api.models.JugadoraResponse
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.theme.PurpleDark

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun JugadoraExplore(
    jugadora: JugadoraResponse,
    isFavorite: Boolean,
    onClick: () -> Unit,
) {

    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(width = 180.dp, height = 240.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            GlideImage(
                model = jugadora.imagen,
                contentDescription = "Imagen de ${jugadora.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(listOf(
                            PurpleDark.copy(alpha = 0.6f),
                            PurpleBack.copy(alpha = 0.6f),
                        ))
                    ),
            )
            Text(
                text = jugadora.nombre.capitalizeWords(),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 140.dp, start = 2.dp, end = 2.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            )
        }
    }
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
    it.lowercase().replaceFirstChar { c -> c.uppercase() }
}