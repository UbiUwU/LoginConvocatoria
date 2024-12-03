package com.example.loginconvocatoria.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginconvocatoria.R

sealed class ScreenN(val route: String) {
    object MenuScreen : ScreenN("menu")
    object DetailScreen : ScreenN("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}

// Composable principal con la configuración de navegación
@Composable
fun MainScreenN(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenN.MenuScreen.route) {
        composable(ScreenN.MenuScreen.route) {
            Notifications()
        }
        composable(ScreenN.DetailScreen.route) {
            // No se utiliza en esta implementación simplificada
        }
    }
}

@Composable
fun Notifications() {
    val cardsData = listOf(
        NotificationCardData("¡Te invitamos a participar en la consulta pública de la Agenda Regulatoria!", R.drawable.not1),
        NotificationCardData("XIII ENCUENTRO ORDINARIO DE LA RED IBEROAMERICANAY DEL CARIBE DE MEJORA REGULATORIA", R.drawable.not2),
        NotificationCardData("Como parte del Nuevo Acuerdo por el Bienestar y Desarrollo de Quintana Roo, seguimos trabajando en colaboración con los Ayuntamientos.", R.drawable.not3),
        NotificationCardData("La Comisión Estatal de Mejora Regulatoria te invita a participar en la Consulta Pública de los Programas de Mejora Regulatoria.", R.drawable.not4)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6)) // Fondo general claro
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Column {
            Text(
                text = "Notificaciones",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF3B3B3B), // Texto más oscuro
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cardsData.size) { index ->
                    val cardData = cardsData[index]
                    ImprovedNotificationCard(title = cardData.title, imageRes = cardData.imageRes)
                }
            }
        }
    }
}

@Composable
fun ImprovedNotificationCard(title: String, imageRes: Int) {
    // Estado para controlar el diálogo
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF3B3B3B),
                modifier = Modifier
                    .padding(12.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), // Espaciado alrededor del botón
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .background(Color(0xFFE0E0E0)) // Fondo gris claro para resaltar
                        .padding(horizontal = 8.dp, vertical = 4.dp) // Aumenta la zona de clic
                ) {
                    Text("Ver más", color = Color.Black) // Texto negro sobre fondo claro
                }
            }
        }
    }

    // Mostrar diálogo con imagen completa
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cerrar", color = Color.Black)
                }
            },
            text = {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        )
    }
}


// Datos de una tarjeta
data class NotificationCardData(val title: String, val imageRes: Int)

@Composable
fun SimpleNotificationCard(title: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Altura fija para un diseño consistente
        shape = MaterialTheme.shapes.medium, // Bordes redondeados
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Fondo blanco
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Encabezado con menor espacio
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), // Reduce el padding
                color = Color.Black
            )
            // Imagen con esquinas redondeadas
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop, // Ajustar la imagen al tamaño del contenedor
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.medium) // Esquinas redondeadas
            )
        }
    }
}

@Composable
fun AppNavigationN() {
    val navController = rememberNavController()
    MainScreenN(navController = navController)
}
