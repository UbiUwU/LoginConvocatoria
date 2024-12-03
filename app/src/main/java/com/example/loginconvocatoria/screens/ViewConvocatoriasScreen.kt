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
import androidx.compose.ui.text.font.FontWeight
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
            .padding(horizontal = 16.dp) // Espaciado lateral
    ) {
        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 60.dp, max =60.dp) // Altura ajustada para el encabezado
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center // Centrar el texto en el Box
        ) {
            Text(
                text = "Agendas Regulatorias Periodo JULIO 2024",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }

        // Contenido
        if (solicitudes.isEmpty()) {
            // Texto de carga
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cargando solicitudes...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            // Lista de tarjetas
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp) // Separación entre el encabezado y la lista
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
    }
}


@Composable
fun SolicitudCard(solicitud: Solicitud, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp), // Bordes ligeramente redondeados
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Fondo blanco
            contentColor = Color.Black // Color del texto
        ),
        elevation = CardDefaults.cardElevation(8.dp) // Elevación para sombra
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Llenar todo el espacio del card
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Espaciado uniforme
        ) {
            // Título de la solicitud
            Text(
                text = solicitud.nombre_preeliminar,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Información adicional
            Text(
                text = "Materia: ${solicitud.materia_regulacion}",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = Color.Gray,
                maxLines = 1
            )

            Text(
                text = "Fecha: ${solicitud.fecha_presentacion}",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = Color.Gray,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fragmento de descripción
            Text(
                text = solicitud.descripcion_propuesta.take(80) + "...",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
