package com.example.loginconvocatoria.ui

import androidx.compose.foundation.layout.* // Para layouts
import androidx.compose.foundation.lazy.LazyColumn // Lista perezosa
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel // Para usar ViewModel en Compose
import com.example.loginconvocatoria.models.Solicitud
import com.example.loginconvocatoria.viewmodels.ViewModelSolicitud
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.sp

@Composable
fun ViewConvocatorias(
    navController: NavController,
    viewModel: ViewModelSolicitud = viewModel() // Inyecta el ViewModel
) {
    // Estado de las solicitudes
    val solicitudes by viewModel.solicitudes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Comprobamos si hay datos o si están cargando
        if (solicitudes.isEmpty()) {
            // Mostramos texto de carga
            Text(
                text = "Cargando solicitudes...",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            // Muestra las solicitudes en una lista
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(solicitudes) { solicitud ->
                    SolicitudCard(
                        solicitud = solicitud,
                        onClick = {
                            navController.navigate("detail/${solicitud.id}") // Navega al detalle
                        }
                    )
                }
            }
        }

//        // Botón flotante para agregar nuevas agendas
//        FloatingActionButton(
//            onClick = { navController.navigate("Creation_Agend") },
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(vertical = 16.dp),
//            containerColor = MaterialTheme.colorScheme.primary
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "Agregar agenda"
//            )
        }
    }
//}

// Composable para mostrar los datos cada solicitud en una tarjeta
@Composable
fun SolicitudCard(solicitud: Solicitud, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp), // Bordes redondeados
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF800020), // Color rojovino de fondo
            contentColor = Color.White // Color del texto
        ),
        elevation = CardDefaults.cardElevation(12.dp) // Elevación para sombra
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Título de la solicitud
            Text(
                text = solicitud.nombre_preeliminar,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                maxLines = 1, // Limitar a una línea
                overflow = TextOverflow.Ellipsis // Agregar "..." si el texto es demasiado largo
            )

            // Línea divisora
            Divider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)

            // Información adicional
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Materia: ${solicitud.materia_regulacion}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "Fecha: ${solicitud.fecha_presentacion}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
                // Icono o elemento decorativo
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = solicitud.id.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }

            // Fragmento de descripción
            Text(
                text = solicitud.descripcion_propuesta.take(120) + "...",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = Color.White.copy(alpha = 0.9f),
                maxLines = 3, // Mostrar hasta 3 líneas de descripción
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}