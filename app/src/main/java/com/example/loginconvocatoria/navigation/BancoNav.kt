package com.example.loginconvocatoria.navigation

import DashboardScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginconvocatoria.screens.DudasInformacion
import com.example.loginconvocatoria.screens.HomeScreen
import com.example.loginconvocatoria.ui.ui.PageContent
import com.example.loginconvocatoria.screens.Ruta1
import com.example.loginconvocatoria.screens.Ruta2
import com.example.loginconvocatoria.screens.Ruta3
import com.example.loginconvocatoria.screens.SuccessScreen
import com.example.loginconvocatoria.ui.CreateSolicitudScreen

import com.example.loginconvocatoria.ui.ui.AgendasFinal

import com.example.loginconvocatoria.ui.ui.LoginScreen
import com.example.loginconvocatoria.ui.ui.Notifications

@Composable
fun BancoNav(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "login_screen"
    ) {
        composable("login_screen") {
            LoginScreen(navController = navController)
        }


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
            CreateSolicitudScreen(navController = navController)
        }
        composable("success_screen") {
            SuccessScreen(navController = navController)
        }

        composable("Agendas_Final") {
            AgendasFinal(navController = navController)
        }
        composable("Dudas_Informacion") {
            DudasInformacion(navController = navController)
        }
        composable("Dashboard") {
            DashboardScreen(navController = navController)
        }
        composable("Notifications") {
            Notifications()
        }
        agendaVisuaRoute(navController)
    }
}
