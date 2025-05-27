package com.example.baskher_frontend.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baskher_frontend.data.api.BaskherRepository
import com.example.baskher_frontend.data.api.models.JugadoraResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JugadoraViewModel(private val baskherRepository: BaskherRepository) : ViewModel() {


    //Detectar en qué pantalla estamos
    private val _currentTitle = MutableLiveData<String?>()
    val currentTitle: LiveData<String?>
        get() = _currentTitle

    //Detectar el índice para actualizar el tab seleccionado
    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndex: LiveData<Int> get() = _selectedIndex

    // Info de la jugadora seleccionada
    private val _jugadora = MutableLiveData<JugadoraResponse?>()
    val jugadora: LiveData<JugadoraResponse?> get() = _jugadora

    private val _jugadoras = MutableStateFlow<List<JugadoraResponse>>(emptyList())
    val jugadoras = _jugadoras.asStateFlow()

    // Carga la lista de jugadoras desde la API
    fun fetchJugadoras() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = baskherRepository.getJugadorasFromAPI()
                _jugadoras.value = response
            } catch (e: Exception) {
                Log.e("VIEWMODEL", "Error al obtener jugadoras", e)
                _jugadoras.value = emptyList()
            }
        }
    }


    // Cargar los detalles de jugadoras desde la API por su ID
    fun fetchJugadoraById(jugadoraId: Int) {
        viewModelScope.launch {
            val jugadoraDetails = withContext(Dispatchers.IO) {
                try {
                    baskherRepository.getJugadoraByIdFromAPI(jugadoraId)
                } catch (e: Exception) {
                    Log.e("VIEWMODEL", "Error al obtener jugadora desde la API: ", e)
                    null
                }
            }
            _jugadora.value = jugadoraDetails
            jugadoraDetails?.let { setCurrentScreenTitle(it.nombre) }
            Log.d("VIEWMODEL", "Película obtenida desde la API: $jugadoraDetails")
        }
    }




    // Cambia el título de la appbar
    fun setCurrentScreenTitle(title: String){
        _currentTitle.value = title
    }

    // Notifica a la tab cuál ha de estar seleccionada
    fun selectIndex(index: Int){
        _selectedIndex.value = index
    }
}