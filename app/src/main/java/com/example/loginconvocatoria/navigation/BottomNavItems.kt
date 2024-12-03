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
//Aqui se le da nombre a los iconos situaos en el bottombar
val bottomNavItems = listOf(
    BottomNavItem("", "Creation_Agend", Icons.Default.Add),
    BottomNavItem("", "Dashboard", Icons.Default.Home),
    BottomNavItem("", "Notifications", Icons.Default.Notifications)
)
