package com.example.loginconvocatoria.models


data class UserProfile(
    val idUsuario: Int,
    val nombreUsuario: String,
    val email: String,
    val rol: String
) {
    companion object {
    }
}

