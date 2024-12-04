package com.example.loginconvocatoria.models


import android.content.Context

data class UserProfile(
    val idUsuario: Int,
    val nombreUsuario: String,
    val email: String,
    val rol: String
) {
    companion object {
        fun fromSharedPreferences(context: Context): UserProfile {
            val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            return UserProfile(
                idUsuario = sharedPref.getInt("idUsuario", -1),
                nombreUsuario = sharedPref.getString("nombreUsuario", "") ?: "",
                email = sharedPref.getString("email", "") ?: "",
                rol = sharedPref.getString("rol", "") ?: ""
            )
        }
    }
}

