package com.example.loginconvocatoria.models

data class UserLoginEntity(
    val id: Int,
    val email: String,
    val token: String,
    val nombreUsuario: String,
    val rol: String
)
