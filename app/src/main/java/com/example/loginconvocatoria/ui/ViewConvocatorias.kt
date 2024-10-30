package com.example.loginconvocatoria.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.loginconvocatoria.ui.ui.PageContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

// Definimos las rutas de las pantallas
sealed class Screen(val route: String) {
    object MenuScreen : Screen("menu")
    object DetailScreen : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
    object PageContent : Screen("page_content") // Nueva pantalla
    object CreationAgend : Screen("Creation_Agend") // Nueva pantalla
}

// Composable principal con la configuración de navegación
@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MenuScreen.route) {
        composable(Screen.MenuScreen.route) {
            ViewConvocatorias(navController)
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType; defaultValue = -1 })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            DetailScreen(itemId)
        }
        composable(Screen.PageContent.route) {
            PageContent() // Asegúrate de que PageContent no requiera parámetros
        }
    }
}

@Composable
fun ViewConvocatorias(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Para ocupar todo el espacio disponible
        ) {
            items(10) { index ->  // Aquí se crean 10 tarjetas como ejemplo
                MenuCard(
                    title = "Nombre generico para la agenda $index",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed magna elit, sagittis vel feugiat eu, vehicula et dui. " +
                            "Nulla tempus, arcu et congue porttitor, felis ligula feugiat mi, a euismod neque erat non orci. " +
                            "Aliquam neque odio, malesuada eu placerat non, iaculis cursus nisi. Praesent laoreet enim ac sagittis auctor. " +
                            "Aenean nibh lacus, maximus id imperdiet sit amet, vehicula ut felis. Curabitur commodo non massa at congue. " +
                            "Fusce turpis lacus, sollicitudin ut magna ac, " +
                            "condimentum maximus urna. Sed eleifend consectetur nunc, nec feugiat quam feugiat et. $index",
                    onClick = {
                        navController.navigate("page_content") // Navegar a PageContent
                    }
                )
            }
        }

        // Botón circular flotante con icono de "+"
        FloatingActionButton(
            onClick = { navController.navigate("Creation_Agend") }, // Navegar a PageContent
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar"
            )
        }
    }
}

// Composable para una tarjeta de menú
@Composable
fun MenuCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Centrar verticalmente
        ) {
            // Ícono vacío por ahora, puedes reemplazarlo con una imagen
            Box(
                modifier = Modifier
                    .size(48.dp) // Tamaño del ícono
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)) // Fondo de ejemplo
            )

            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el ícono y el contenido

            // Contenido de la tarjeta
            Column {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// Pantalla de detalle de la tarjeta
@Composable
fun DetailScreen(itemId: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Detail of item with ID: $itemId", style = MaterialTheme.typography.titleLarge)
    }
}

// Punto de entrada para la navegación
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}
