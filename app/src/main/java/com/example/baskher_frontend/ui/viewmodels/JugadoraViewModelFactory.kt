package com.example.baskher_frontend.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baskher_frontend.data.api.BaskherRepository

class JugadoraViewModelFactory(
    private val baskherRepository: BaskherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create (modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(JugadoraViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JugadoraViewModel(baskherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}