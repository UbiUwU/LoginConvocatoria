package com.example.loginconvocatoria.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.models.MenuLateral
import com.example.loginconvocatoria.navigation.currentRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu_Lateral(
    navController: NavHostController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val menuItems = listOf(
        MenuLateral.Home,
        MenuLateral.Ruta1
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight(0.9f) // Ajusta la altura del menú lateral
                    .fillMaxWidth(0.6f) // Ajusta el ancho del menú lateral)
                    .clip(RoundedCornerShape(1.dp)) // Esquinas redondeadas
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Título del menú lateral
                    Text(
                        text = "Menú",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Opciones de navegación
                    menuItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(item.icon, contentDescription = null)
                            },
                            label = { Text(text = item.title) },
                            selected = currentRoute(navController) == item.route,
                            onClick = {
                                navigateToRoute(navController, item.route, drawerState, scope)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp) // Espaciado vertical
                        )
                    }
                }
            }
        }
    ) {
        contenido()
    }
}

private fun navigateToRoute(
    navController: NavHostController,
    route: String,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    scope.launch {
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
        drawerState.close() // Cierra el menú lateral después de la navegación
    }
}
