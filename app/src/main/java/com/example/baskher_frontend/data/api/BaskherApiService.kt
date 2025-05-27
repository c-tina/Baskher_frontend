package com.example.baskher_frontend.data.api

import com.example.baskher_frontend.data.api.models.JugadoraResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface BaskherApiService {
    @GET("baskher/jugadoras")
    suspend fun getJugadoras(
    ) : Response<List<JugadoraResponse>>

    @GET("baskher/jugadoras/{jugadora_id}")
    suspend fun getJugadorasById(
        @Path("jugadora_id") id: Int
    ) : Response<JugadoraResponse>
}