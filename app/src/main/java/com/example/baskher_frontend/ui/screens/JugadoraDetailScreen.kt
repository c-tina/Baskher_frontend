package com.example.baskher_frontend.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.baskher_frontend.R
import com.example.baskher_frontend.ui.components.JugadoraMainInformation
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel

@Composable
fun JugadoraDetailScreen(
    jugadoraId: Int,
    viewModel: JugadoraViewModel,
    onBackClick: () -> Unit
) {

    viewModel.fetchJugadoraById(jugadoraId)


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
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box {
                JugadoraMainInformation(jugadora)
                IconButton(
                    onClick = { onBackClick() },
                    modifier = Modifier
                        .padding(top = 50.dp, start = 10.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_24),
                        contentDescription = "Volver",
                        tint = PurpleDark
                    )
                }
            }
        }
    }
}
