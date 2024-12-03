
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.loginconvocatoria.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val items = listOf(
        DashboardItem("Perfil", R.drawable.dash1),
        DashboardItem("Configuración", R.drawable.dash2),
        DashboardItem("Notificaciones", R.drawable.dash3),
        DashboardItem("Ayuda", R.drawable.dash4),
        // Agrega más elementos según necesites
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally // Mantén el centrado horizontal
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Dos columnas
            verticalArrangement = Arrangement.SpaceEvenly, // Distribuye verticalmente
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                DashboardCard(item, navController)
            }
        }
    }
}



@Composable
fun DashboardCard(item: DashboardItem, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Mantiene las tarjetas cuadradas
            .clickable {
                // Maneja la navegación según el elemento
                when (item.title) {
                    "Perfil" -> navController.navigate("profile_screen")
                    "Configuración" -> navController.navigate("settings_screen")
                    "Notificaciones" -> navController.navigate("notifications_screen")
                    "Ayuda" -> navController.navigate("help_screen")
                    // Agrega más casos según necesites
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Fondo blanco para la tarjeta
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = item.iconRes),
                contentDescription = item.title,
                modifier = Modifier.size(64.dp),
                tint = Color.Red // Ícono de color rojo
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class DashboardItem(
    val title: String,
    val iconRes: Int
)