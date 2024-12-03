package com.example.loginconvocatoria.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginconvocatoria.R
import com.example.loginconvocatoria.api.LoginRetrofitClient
import com.example.loginconvocatoria.models.SolicitudAgenda
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun GestionarAgendaScreen(navController: NavController) {
    val context = LocalContext.current
    var solicitudes by remember { mutableStateOf<List<SolicitudAgenda>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Cargar las solicitudes desde la API
    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("APP_PREF", android.content.Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKEN", "") ?: ""

        LoginRetrofitClient.instance.getSolicitudes("Bearer $token").enqueue(object : Callback<List<SolicitudAgenda>> {
            override fun onResponse(
                call: Call<List<SolicitudAgenda>>,
                response: Response<List<SolicitudAgenda>>
            ) {
                if (response.isSuccessful) {
                    solicitudes = response.body().orEmpty()
                    isLoading = false
                } else {
                    errorMessage = "Error: ${response.code()} - ${response.message()}"
                    isLoading = false
                }
            }

            override fun onFailure(call: Call<List<SolicitudAgenda>>, t: Throwable) {
                errorMessage = "Error: ${t.message}"
                isLoading = false
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Gestionar Agendas",
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(id = R.color.rojo_vino)
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colorResource(id = R.color.rojo_vino))
            }
        } else if (errorMessage != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(solicitudes) { index, solicitud ->
                    SolicitudCard(
                        solicitud = solicitud,
                        onSolicitudAction = { accion, comentario ->
                            // Simular la eliminación de la solicitud de la lista
                            solicitudes = solicitudes.toMutableList().also { it.removeAt(index) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SolicitudCard(
    solicitud: SolicitudAgenda,
    onSolicitudAction: (accion: String, comentario: String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showCommentDialog by remember { mutableStateOf(false) }
    var comentario by remember { mutableStateOf("") }
    var accionSeleccionada by remember { mutableStateOf<String?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
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
                text = solicitud.descripcion_propuesta,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Información adicional básica
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

            // Información adicional desplegada
            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))

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

                Spacer(modifier = Modifier.height(16.dp))

                // Botones de acción mejorados
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = {
                            accionSeleccionada = "Aprobar"
                            showCommentDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Aprobar")
                    }
                    OutlinedButton(
                        onClick = {
                            accionSeleccionada = "Devolver a Revisión"
                            showCommentDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFFFC107),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Devolver a Revisión")
                    }
                    OutlinedButton(
                        onClick = {
                            accionSeleccionada = "Rechazar"
                            showCommentDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFF44336),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Rechazar")
                    }
                }
            }

            // Mostrar diálogo de comentario si una acción ha sido seleccionada
            if (showCommentDialog) {
                AlertDialog(
                    onDismissRequest = { showCommentDialog = false },
                    title = {
                        Text(text = "Comentario - $accionSeleccionada")
                    },
                    text = {
                        Column {
                            Text(text = "Por favor, ingresa un comentario para la acción seleccionada.")
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = comentario,
                                onValueChange = { comentario = it },
                                label = { Text("Comentario") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showCommentDialog = false
                                // Simular la acción tomada y eliminar la solicitud de la lista
                                onSolicitudAction(accionSeleccionada ?: "", comentario)
                                comentario = ""
                            }
                        ) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showCommentDialog = false
                                comentario = ""
                            }
                        ) {
                            Text("Cancelar")
                        }
                    },
                    containerColor = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
    }
}
