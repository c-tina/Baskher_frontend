package com.example.baskher_frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.baskher_frontend.data.api.models.JugadoraResponse
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.theme.PurpleGrey40
import com.example.baskher_frontend.ui.theme.PurpleMedium

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun JugadoraMainInformation(jugadora: JugadoraResponse) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenHeight = displayMetrics.heightPixels
    val screenHeightDp = with(LocalDensity.current) { screenHeight.toDp() }

    Column(
        Modifier
            .fillMaxWidth()
            .height(screenHeightDp)
    ) {
        ConstraintLayout {
            val information = createRef()

            Box {
                GlideImage(
                    model = jugadora.imagen,
                    contentDescription = "Imagen de ${jugadora.nombre}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(screenHeightDp)
                        .fillMaxWidth()
                        .background(PurpleBack)
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                brush = Brush.linearGradient(
                                    0.0f to PurpleBack.copy(alpha = 1f),
                                    0.4f to PurpleBack.copy(alpha = 0.9f),
                                    0.8f to Color.Transparent,
                                    start = Offset(0.0f, size.height),
                                    end = Offset(0.0f, size.height * 0.2f)
                                )
                            )
                        }
                )
            }

            Column(
                Modifier
                    .padding(25.dp)
                    .constrainAs(information) {
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Text(
                    text = jugadora.nombre.uppercase(),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark,
                    fontSize = 38.sp
                )

                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = jugadora.equipo,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = PurpleMedium,
                    fontSize = 25.sp
                )

                Row(Modifier.fillMaxWidth()) {
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(bottom = 30.dp),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Text("Puntos: ${jugadora.puntos_totales}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = PurpleGrey40,
                        )

                        Text("Asistencias: ${jugadora.asistencias}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = PurpleGrey40,
                        )
                        Text("Rebotes: ${jugadora.rebotes_totales}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = PurpleGrey40,
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text("T2: ${jugadora.porcentaje_t2} %",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = PurpleGrey40,
                            )
                        Text("T3: ${jugadora.porcentaje_t3} %",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = PurpleGrey40,
                        )
                        Text("TL: ${jugadora.porcentaje_tl} %",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = PurpleGrey40,
                        )
                    }
                }
            }
        }
    }
}
