package com.example.loginconvocatoria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginconvocatoria.models.Solicitud
import com.example.loginconvocatoria.viewmodels.ViewModelSolicitud

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
                .heightIn(min = 60.dp, max = 75.dp) // Altura ajustada para el encabezado
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
                    SolicitudCard(solicitud = solicitud) // Llama a la tarjeta sin el parámetro onClick
                }
            }
        }
    }
}

@Composable
fun SolicitudCard(solicitud: Solicitud) {
    // Estado para controlar si la tarjeta está expandida
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded }, // Cambia el estado al hacer clic
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Fondo blanco
            contentColor = Color.Black // Color del texto
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Título de la solicitud
            Text(
                text = solicitud.nombre_preeliminar,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Información adicional
            Text(
                text = "Responsable: ${solicitud.nombre_responsable_propuesta}",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = Color.Gray,
                maxLines = 1
            )

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


            if (isExpanded) {
                // Información adicional desplegada
                Spacer(modifier = Modifier.height(16.dp))

//                Text(
//                    text = "Acción Regulatoria: ${solicitud.accion_regulatoria}",
//                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
//                    color = Color.Black
//                )

                Text(
                    text = "Cargo Responsable Propuesta: ${solicitud.cargo_responsable_propuesta}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
                Text(
                    text = "Descripción: ${solicitud.descripcion_propuesta}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
                Text(
                    text = "Problemática: ${solicitud.problematica_propuesta}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
                Text(
                    text = "Justificación: ${solicitud.justificacion_propuesta}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
                Text(
                    text = "Beneficio: ${solicitud.beneficio_propuesta}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
                Text(
                    text = "Fundamento Jurídico: ${solicitud.fundamento_juridico}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
//                Text(
//                    text = "Fecha Tentativa Presentación: ${solicitud.fecha_tentativa_presentacion}",
//                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
//                    color = Color.Black
//                )
//                Text(
//                    text = "Fecha Tentativa Publicación: ${solicitud.fecha_tentativa_publicacion}",
//                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
//                    color = Color.Black
//                )
//                Text(
//                    text = "Publicación: ${solicitud.publicacion}",
//                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
//                    color = Color.Black
//                )
            }
        }
    }
}
