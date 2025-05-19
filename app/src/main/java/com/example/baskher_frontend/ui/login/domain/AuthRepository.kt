package com.example.baskher_frontend.ui.login.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, password: String): Result<Unit>
    fun logout()
    fun isUserLoggedIn(): Boolean
}