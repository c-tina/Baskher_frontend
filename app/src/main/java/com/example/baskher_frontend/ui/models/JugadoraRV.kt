package com.example.baskher_frontend.ui.models

data class JugadoraRV (
    val id: Int,
    val nombre: String,
    val equipo: String,
    val imagen: String,
    val puntos_totales : Int,
    val asistencias: Int,
    val rebotes_totales: Int,
    val porcentajes_t2: Float,
    val porcentajes_t3: Float,
    val porcentajes_t1: Float,
)


