package com.example.loginconvocatoria.models

data class RegisterRequest(
    val nombreUsuario: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val email: String,
    val password: String,
    val idDependencia: Int?,
    val rol: String
)
