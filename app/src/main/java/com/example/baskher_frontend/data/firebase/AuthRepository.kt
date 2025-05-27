package com.example.baskher_frontend.data.firebase

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, password: String): Result<Unit>
    fun logout()
    fun isUserLoggedIn(): Boolean
}