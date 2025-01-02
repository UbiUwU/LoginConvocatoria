package com.example.loginconvocatoria.models

data class Usuario(
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