package com.example.loginconvocatoria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginconvocatoria.screens.HomeScreen
import com.example.loginconvocatoria.ui.ui.PageContent
import com.example.loginconvocatoria.screens.Ruta1
import com.example.loginconvocatoria.screens.Ruta2
import com.example.loginconvocatoria.screens.Ruta3
import com.example.loginconvocatoria.ui.ui.CreationAgend

@Composable
fun BancoNav(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(navController = navController)
        }
        composable("ruta1") {
            Ruta1(navController = navController)
        }
        composable("ruta2") {
            Ruta2(navController = navController)
        }
        composable("ruta3") {
            Ruta3(navController = navController)
        }
        composable("page_content") {
            PageContent()
        }
        composable("Creation_Agend") {
            CreationAgend(navController = navController)
        }

    }
}
