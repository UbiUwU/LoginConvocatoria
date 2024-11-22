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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.loginconvocatoria.ui.ui.PageContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog

// Definimos las rutas de las pantallas
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Notificaciones",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(10) { index ->
                NotificationCard(
                    title = "Notificación $index",
                    description = "Esta es una descripción breve de la notificación. Puedes tocarla para más detalles.",
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

        // Mostrar diálogo si hay una notificación seleccionada
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

@Composable
fun NotificationCard(title: String, description: String, date: String, time: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$date • $time",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
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
                .height(400.dp) // Aumenta la altura del cuadro de diálogo
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${notification.date} • ${notification.time}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = notification.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f) // Usar el espacio disponible
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Cerrar")
                    }
                    Button(
                        onClick = onMarkAsSeen
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
