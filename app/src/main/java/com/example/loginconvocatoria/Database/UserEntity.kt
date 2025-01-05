package com.example.loginconvocatoria.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val token: String,
    val nombreUsuario: String,
    val rol: String
)
