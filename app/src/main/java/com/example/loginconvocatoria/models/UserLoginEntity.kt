package com.example.loginconvocatoria.models

data class UserLoginEntity(
    val id: Int,
    val email: String,
    val token: String,
    val nombreUsuario: String,
    val rol: String
)



//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "user_login")
//data class UserLoginEntity(
//    @PrimaryKey val id: Int,
//    val email: String,
//    val token: String,
//    val nombreUsuario: String,
//    val rol: String
//)