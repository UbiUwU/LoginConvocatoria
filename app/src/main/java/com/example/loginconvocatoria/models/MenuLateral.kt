package com.example.loginconvocatoria.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.loginconvocatoria.navigation.NavScreen

sealed class MenuLateral(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    object Home : MenuLateral(Icons.Outlined.Home, "Lista de agendas", NavScreen.HomeScreen.route)
    object Ruta1 : MenuLateral(Icons.Outlined.Add, "inicio de sesion", NavScreen.Ruta1.route)
    object Ruta2 : MenuLateral(Icons.Outlined.Add, "Registro", NavScreen.Ruta2.route)
    object PageContent : MenuLateral(Icons.Outlined.Add, "Informacion de la agenda", NavScreen.PageContent.route)
    object CreationAgend : MenuLateral(Icons.Outlined.Add, "Creacion de agenda", NavScreen.CreationAgend.route)
    // Agrega otras rutas según sea necesario
}
