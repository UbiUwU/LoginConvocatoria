package com.example.loginconvocatoria.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import com.example.loginconvocatoria.R


sealed class ScreenN(val route: String) {
    object MenuScreen : ScreenN("menu")
    object DetailScreen : ScreenN("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
    object PageContent : ScreenN("page_content") // Nueva pantalla
    object CreationAgend : ScreenN("Creation_Agend") // Nueva pantalla
}

// Composable principal con la configuración de navegación
@Composable
fun MainScreenN(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MenuScreen.route) {
        composable(Screen.MenuScreen.route) {
            Notifications()
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType; defaultValue = -1 })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            DetailScreenN(itemId)
        }
        composable(Screen.PageContent.route) {
            PageContent() // Asegúrate de que PageContent no requiera parámetros
        }
    }
}

@Composable
fun Notifications() {
    var selectedNotification by remember { mutableStateOf<NotificationData?>(null) }

    // Fondo de pantalla con un encabezado visualmente atractivo
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Encabezado
            Text(
                text = "Notificaciones",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black, // Letras en negro para mejor visibilidad
                modifier = Modifier
                    .padding(vertical = 16.dp) // Espaciado alrededor del texto
                    .align(Alignment.CenterHorizontally) // Centrado horizontal
            )

            // Lista de notificaciones
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
            ) {
                items(10) { index ->
                    NotificationCard(
                        title = "Notificación $index",
                        description = "Descripción breve de la notificación. Toca para más detalles.",
                        date = "2024-11-21",
                        time = "14:35",
                        onClick = {
                            selectedNotification = NotificationData(
                                title = "Notificación $index",
                                description = "Esta es la descripción completa de la notificación $index. Aquí puedes ver más información detallada.",
                                date = "2024-11-21",
                                time = "14:35"
                            )
                        }
                    )
                }
            }

            // Diálogo de notificación seleccionada
            selectedNotification?.let { notification ->
                NotificationDialog(
                    notification = notification,
                    onDismiss = { selectedNotification = null },
                    onMarkAsSeen = {
                        selectedNotification = null
                    }
                )
            }
        }
    }
}

@Composable
fun NotificationCard(
    title: String,
    description: String,
    date: String,
    time: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp), // Márgenes ajustados para tarjetas más grandes
        elevation = CardDefaults.cardElevation(6.dp), // Suavizar la sombra pero mantener un poco
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.small // Bordes más cuadrados
    ) {
        Row(
            modifier = Modifier.padding(20.dp), // Padding interno más amplio
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color(0xFF8B0000), // Rojo vino
                modifier = Modifier.size(50.dp) // Ícono más grande
            )

            Spacer(modifier = Modifier.width(20.dp)) // Mayor espacio entre el ícono y el texto
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black, // Color negro para el título
                    maxLines = 1, // Limitar el título a una línea
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp)) // Más espacio entre líneas
                Text(
                    text = "$date en $description",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    maxLines = 2, // Descripción limitada a dos líneas
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}



@Composable
fun NotificationDialog(
    notification: NotificationData,
    onDismiss: () -> Unit,
    onMarkAsSeen: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${notification.date} • ${notification.time}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = notification.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(text = "Cerrar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onMarkAsSeen,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = "Visto")
                    }
                }
            }
        }
    }
}


// Clase de datos para manejar las notificaciones
data class NotificationData(
    val title: String,
    val description: String,
    val date: String,
    val time: String
)



// Pantalla de detalle de la tarjeta
@Composable
fun DetailScreenN(itemId: Int) {
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
fun AppNavigationN() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}
