package com.example.loginconvocatoria.models



data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val message: String,
    val token: String,
    val usuario: Usuario
)
