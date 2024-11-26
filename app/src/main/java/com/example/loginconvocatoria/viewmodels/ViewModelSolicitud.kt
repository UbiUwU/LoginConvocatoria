package com.example.loginconvocatoria.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginconvocatoria.api.ApiService
import com.example.loginconvocatoria.api.RetrofitInstance
import com.example.loginconvocatoria.models.Solicitud
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class ViewModelSolicitud : ViewModel() {

    private val _solicitudes = MutableStateFlow<List<Solicitud>>(emptyList())
    val solicitudes: StateFlow<List<Solicitud>> = _solicitudes

    private fun fetchSolicitudes() {
        viewModelScope.launch {
            try {
                val credentials = mapOf(
                    "email" to "keflok23@gmail.com",
                    "password" to "keflok23@gmail.com"
                )

                val response = RetrofitInstance.api.getNucleoDigital(
                    token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiZDBhZjdmMDUxOTYwZGI4OTllNzQxMTMyMjYwM2JhMzk5OGY1YWIwN2FjZmYxY2M5MTZmMmRhMGQ3OTY1M2RiYzRlODVmYjc3ZjFlNjc4M2YiLCJpYXQiOjE3MzIyMzIxNDYuMTMwMjg5LCJuYmYiOjE3MzIyMzIxNDYuMTMwMjk1LCJleHAiOjE3NjM3NjgxNDUuODcxNjUzLCJzdWIiOiIzOSIsInNjb3BlcyI6W119.nLJDxuvT5HVQAJheaUX5J8DRhCAfuQzWkMEj6pbppSwEbr2xY-RBT16EWvXxJtoX1lrQJ5K7gRaFjntEAuROWvZ443ErQgX-1oi8awzG1DA-nLYRMaMrL6rqZLNiQJ7IbUhM-ihJgGWzUQ7Pu8xpYKnjuGWazR55GnQjBRop0EcUlUMymfIGZOjRA9HfL_N_hEv0QPqUFNKnGdkR3MefgUvG5X9rn5hdKQ6BS84P7dcQhxDdJ3rjZMRNh5KWrKHtTZnuqBqXQLiaXXa43xIhjaVNqWmxLYk2_sHQQn3_Mnxdth9Yw3Dqp-KrEVLQBHdm8qS23yFblBxrULyx5n1ixpx036ZCktb49EjUH7kY4fdrnwvzUl1J7g53r9jpsQDk9egjLGoKRxdhEk8g7hhn1hXnrHlEhbIVrsoXJa4dtkvsp1xsQSQf8KZsByK88GV4U0kmMO8mPBP8LH6o2u9lP05MP6u5GB6LmdLeI-kfhfO6YsONKqpRDJS4z-p9tal7RdxrfEZVeEsYmU-QOwIBmBQOFFKcRcS9-cVQSr8SbgY1B88Ei96LYfvzsmhwzEu10ljqgcJMt71FwRdoyFkho3y1NGFx8dCyNk-lItso6sMBz8ns2NcayptsZtGd3LE78qPdRpPPe564orD2ct1-W3a75z88U93hq9sC4z2AGc4", // Reemplaza con tu token válido
                    credentials = credentials
                )

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.datosTablas?.comedatos_mapachesReguladores_solicitud?.let {
                        _solicitudes.value = it // Actualiza con las solicitudes obtenidas
                    }
                } else {
                    Log.e("fetchSolicitudes", "Error en la respuesta: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("fetchSolicitudes", "Excepción al llamar la API: ${e.message}")
            }
        }
    }

    init {
        fetchSolicitudes() // Se ejecuta automáticamente cuando se crea el ViewModel
    }
}
