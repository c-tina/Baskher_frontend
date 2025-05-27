package com.example.baskher_frontend.data.api

import com.example.baskher_frontend.data.api.models.JugadoraResponse

class BaskherRepository(private val baskherApiService : BaskherApiService) {


    suspend fun getJugadorasFromAPI():List<JugadoraResponse> {
        val response = baskherApiService.getJugadoras()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception ("La lista de jugadoras es nula")
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun getJugadoraByIdFromAPI(id : Int) : JugadoraResponse {
        val response = baskherApiService.getJugadorasById(id)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("La jugadora con ID $id es nula")
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }
}