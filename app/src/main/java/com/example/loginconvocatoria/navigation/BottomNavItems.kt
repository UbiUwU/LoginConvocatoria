package com.example.loginconvocatoria.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Inicio", "home_screen", Icons.Default.Home),
    BottomNavItem("Ruta 1", "ruta1", Icons.Default.List),
    BottomNavItem("Ruta 2", "ruta2", Icons.Default.Add),
    BottomNavItem("Notificaciones", "Notifications", Icons.Default.Notifications)
)
