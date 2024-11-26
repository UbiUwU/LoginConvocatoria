package com.example.loginconvocatoria.models


data class Institucion(
    val IdUbicacion: String,
    val Nombre_Institucion: String,
    val Direccion: String,
    val Numero: String,
    val Colonia: String,
    val CodigoPostal: String?,
    val Referencia: String?,
    val Nombre_Encargado: String,
    val ApellidoPaterno: String,
    val ApellidoMaterno: String,
    val Nombramiento: String,
    val Email: String,
    val Telefono: String?,
    val Extension: String?,
    val logo: String
)