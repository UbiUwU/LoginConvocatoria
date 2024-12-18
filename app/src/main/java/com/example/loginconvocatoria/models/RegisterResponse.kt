package com.example.loginconvocatoria.models

data class RegisterResponse(
    val message: String,
    val token: String,
    val usuario: Usuario
)

data class UsuarioRegister(
    val idUsuario: Int,
    val nombreUsuario: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val email: String,
    val idDependencia: Int?,
    val rol: String,
    val estado: Boolean,
    val created_at: String,
    val updated_at: String
)
