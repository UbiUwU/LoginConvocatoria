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

    // Estado para el filtro seleccionado
    var selectedFilter by remember { mutableStateOf("Todas") }
    val categorias = listOf(
        "Todas",
        "Programas sociales, Desarrollo agropecuario.",
        "Recursos humanos.",
        "Fiscal.",
        "Aduanera.",
        "Educación",
        "Armas de fuego y explosivos.",
        "Comercio exterior.",
        "Protección civil.",
        "Derechos e intereses del consumidor.",
        "Infraestructura y/o construcción.",
        "Medio ambiente",
        "Operaciones con recursos de procedencia ilícita",
        "Programas sociales",
        "Protección contra riesgos sanitarios",
        "Proteger la sanidad y la inocuidad agroalimentaria, animal y vegetal",
        "Recursos naturales",
        "Salud",
        "Sector financiero",
        "Seguridad alimentaria",
        "Seguridad de la población",
        "Seguridad de los productos no alimentarios y protección del consumidor",
        "Seguridad social",
        "Seguridad, protección y/ salud laboral",
        "Trabajo",
        "Transporte",
        "Turismo",
        "Desarrollo agropecuario",
        "Competitividad",
        "Administración pública."
    )

    // Filtrar las solicitudes según la categoría seleccionada
    val filteredSolicitudes = if (selectedFilter == "Todas") {
        solicitudes
    } else {
        solicitudes.filter { it.materia_regulacion == selectedFilter }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // Espaciado lateral
    ) {
        // Encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 60.dp, max = 60.dp) // Altura ajustada para el encabezado
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center // Centrar el texto en el Box
        ) {
            Text(
                text = "Agendas Regulatorias Período 2024",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filtrar por Materia:",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow, // Color rojo del botón
                        contentColor = Color.Black // Texto en blanco
                    )
                ) {
                    Text(text = selectedFilter)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categorias.forEach { categoria ->
                        DropdownMenuItem(
                            text = { Text(text = categoria) },
                            onClick = {
                                selectedFilter = categoria
                                expanded = false
                            }
                        )
                    }
                }
            }
        }


        // Contenido
        if (filteredSolicitudes.isEmpty()) {
            // Texto de carga
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay solicitudes para la categoría seleccionada.",
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
                items(filteredSolicitudes) { solicitud ->
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Descripción: ${solicitud.descripcion_propuesta}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = Color.Black
                )
                // Agrega más información expandida aquí si es necesario
            }
        }
    }
}
