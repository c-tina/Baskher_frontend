package com.example.baskher_frontend.data.api.models
import com.google.gson.annotations.SerializedName

data class JugadoraResponse (
    val id: Int,
    val nombre: String,
    val equipo: String,
    @SerializedName("imagen_jugadora")
    val imagen: String,
    val puntos_totales : Int,
    val asistencias: Int,
    val rebotes_totales: Int,
    val porcentaje_t2: Float,
    val porcentaje_t3: Float,
    val porcentaje_tl: Float,
)