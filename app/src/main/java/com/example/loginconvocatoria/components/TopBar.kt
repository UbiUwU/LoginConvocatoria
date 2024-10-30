package com.example.loginconvocatoria.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.models.MenuLateral
import com.example.loginconvocatoria.navigation.currentRoute
import kotlinx.coroutines.launch
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    drawerState: DrawerState,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val currentRoute = currentRoute(navController)
    val title = getTitleForRoute(currentRoute) // Get the title using the function

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(Icons.Outlined.Menu, contentDescription = "abridge el menu")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun getTitleForRoute(route: String?): String {
    return when (route) {
        MenuLateral.Home.route -> MenuLateral.Home.title
        MenuLateral.Ruta1.route -> MenuLateral.Ruta1.title
        MenuLateral.Ruta2.route -> MenuLateral.Ruta2.title
        MenuLateral.PageContent.route -> MenuLateral.PageContent.title
        MenuLateral.CreationAgend.route -> MenuLateral.CreationAgend.title
        // Add more cases for other routes if needed
        else -> "Ruta desconocida" // Or a more appropriate default
    }
}