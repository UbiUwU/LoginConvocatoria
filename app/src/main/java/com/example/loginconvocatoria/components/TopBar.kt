package com.example.loginconvocatoria.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.loginconvocatoria.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    drawerState: DrawerState,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val currentRoute = currentRoute(navController)
    val title = getTitleForRoute(currentRoute) // Obtén el título usando la función

    Box {
        // Imagen de fondo (PNG) ajustada al ancho de la pantalla
        Image(
            painter = painterResource(id = R.drawable.topbar), // Reemplaza con el ID de tu recurso PNG
            contentDescription = null,
            contentScale = ContentScale.FillWidth, // Ajusta la imagen al ancho de la pantalla
            modifier = Modifier
                .fillMaxWidth()
            //.height(56.dp) // Altura de la AppBar
        )

        // Contenido de la AppBar encima de la imagen
        CenterAlignedTopAppBar(
            title = {

            },
            navigationIcon = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Menu,
                            contentDescription = "abrir el menú",
                            tint = Color.White, // Cambia el color del icono de navegación si es necesario
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent, // Haz transparente el fondo de la barra
                navigationIconContentColor = Color.White // Color del icono de navegación
            ),
            modifier = Modifier.background(Color.Transparent) // Asegúrate de que el fondo sea transparente
        )
    }
}

/*
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
            containerColor = colorResource(id = R.color.rojoA),
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
*/



@Composable
fun getTitleForRoute(route: String?): String {
    return when (route) {
        MenuLateral.Home.route -> MenuLateral.Home.title
        MenuLateral.Ruta1.route -> MenuLateral.Ruta1.title
        MenuLateral.Ruta2.route -> MenuLateral.Ruta2.title
        MenuLateral.PageContent.route -> MenuLateral.PageContent.title
        MenuLateral.CreationAgend.route -> MenuLateral.CreationAgend.title
        MenuLateral.AgendaVisua.route -> MenuLateral.AgendaVisua.title
        // Add more cases for other routes if needed
        else -> "Ruta desconocida" //
    }
}