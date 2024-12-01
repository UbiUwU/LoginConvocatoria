package com.example.loginconvocatoria.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceLogin {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
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
