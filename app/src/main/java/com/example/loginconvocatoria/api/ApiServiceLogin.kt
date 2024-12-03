package com.example.loginconvocatoria.api


import com.example.loginconvocatoria.models.SolicitudAgenda
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServiceLogin {

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // Obtener todas las solicitudes
    @GET("solicitudes")
    fun getSolicitudes(
        @Header("Authorization") token: String
    ): Call<List<SolicitudAgenda>>

    // Crear una solicitud
    @POST("solicitudes")
    fun createSolicitud(
        @Header("Authorization") token: String,
        @Body solicitud: SolicitudAgenda
    ): Call<SolicitudAgenda>
}


data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val message: String,
    val token: String,
    val usuario: Usuario
)

data class Usuario(
    val idUsuario: Int,
    val nombreUsuario: String,
    val email: String,
    val rol: String
)