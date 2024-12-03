package com.example.loginconvocatoria.models

data class LoginResponse(
    val message: String,
    val token: String,
    val usuario: Usuario
)

data class Usuario(
    val idUsuario: Int,
    val apellidoPaterno: String,
    val nombreUsuario: String,
    val email: String,
    val rol: String
)