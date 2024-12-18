package com.example.loginconvocatoria.api

import com.example.loginconvocatoria.models.LoginRequest
import com.example.loginconvocatoria.models.LoginResponse
import com.example.loginconvocatoria.models.SolicitudAgenda
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceLogin {
    // Login
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

    // Actualizar una solicitud
    @PUT("solicitudes/{id}")
    fun updateSolicitud(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body solicitud: SolicitudAgenda
    ): Call<SolicitudAgenda>

    // Eliminar una solicitud
    @DELETE("solicitudes/{id}")
    fun deleteSolicitud(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Void>
}

