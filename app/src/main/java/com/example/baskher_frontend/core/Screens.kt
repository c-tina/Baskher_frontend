package com.example.baskher_frontend.core

import kotlinx.serialization.Serializable

@Serializable
object Explore

@Serializable
object Estadisticas

@Serializable
object Perfil

@Serializable
data class JugadoraDetail(
    val jugadoraId: Int
)