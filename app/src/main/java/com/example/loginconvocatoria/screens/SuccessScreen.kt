package com.example.loginconvocatoria.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginconvocatoria.R

@Composable
fun SuccessScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Usamos una Card blanca para darle un fondo limpio al contenido
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ícono de éxito
                Image(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Icono de éxito",
                    modifier = Modifier.size(100.dp)
                )

                // Título principal
                Text(
                    text = "¡Solicitud Enviada!",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.Gray)
                )

                // Mensaje secundario
                Text(
                    text = "Tu solicitud ha sido enviada exitosamente. " +
                            "Pronto recibirás una respuesta.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                // Espacio antes del botón
                Spacer(modifier = Modifier.height(8.dp))

                // Botón para volver al inicio
                Button(
                    onClick = {
                        navController.navigate("create_solicitud_screen") {
                            popUpTo("create_solicitud_screen") { inclusive = true }
                        }
                    }
                ) {
                    Text("Volver al Inicio")
                }
            }
        }
    }
}
