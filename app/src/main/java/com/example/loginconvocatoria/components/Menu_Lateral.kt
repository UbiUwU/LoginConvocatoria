
package com.example.loginconvocatoria.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.loginconvocatoria.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu_Lateral(
    navController: NavHostController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit
) {
    //Aqui se definen las rutas para el menu lateral
    val scope = rememberCoroutineScope()
    val menuItems = listOf(
        MenuLateral.Login,
        MenuLateral.Home,
        MenuLateral.CrearAgendas,
        MenuLateral.Notifications,
        MenuLateral.DudasInformacion
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight(1.0f)
                    .fillMaxWidth(0.70f) // Ancho del menú lateral
                    .background(Color.White) // Fondo blanco para el menú
                    .padding(0.dp) // Sin padding adicional
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp) // Asegura que la columna ocupe todo el espacio sin padding adicional
                ) {
                    // Título del menú
                    Text(
                        text = "Menú",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 24.sp,  // Aumenta el tamaño
                            fontWeight = FontWeight.Bold,  // Negrita para destacar
                            color = colorResource(id = R.color.white)  // Color blanco
                        ),
                        modifier = Modifier
                            .background(colorResource(id = R.color.rojoA))  // Fondo de color
                            .fillMaxWidth()  // Asegura que ocupe todo el ancho
                            .padding(16.dp)  // Espaciado alrededor del texto
                            .padding(start = 16.dp, end = 16.dp)  // Espaciado interno
                    )


                    menuItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(item.icon, contentDescription = null, tint = colorResource(id = R.color.rojoA))
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    color = colorResource(id = R.color.black),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            selected = currentRoute(navController) == item.route,
                            onClick = {
                                navigateToRoute(navController, item.route, drawerState, scope)
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedTextColor = colorResource(id = R.color.white),
                                unselectedTextColor = colorResource(id = R.color.black),
                                selectedIconColor = colorResource(id = R.color.rojoA),
                                unselectedIconColor = colorResource(id = R.color.rojoA),
                                selectedContainerColor = colorResource(id = R.color.rojoA).copy(alpha = 0.1f),
                                unselectedContainerColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f)) // Esto empuja los elementos al tope

                    // Agregar los logos y la versión al final
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Logo del Gob :)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painter = painterResource(id = R.drawable.gobierno), contentDescription = "Gobierno del Estado de México")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Versión 1.0.0", style = MaterialTheme.typography.bodySmall)
                        }
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
        drawerState.close()
    }
}
