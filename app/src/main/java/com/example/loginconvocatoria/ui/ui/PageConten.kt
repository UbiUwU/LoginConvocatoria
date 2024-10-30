package com.example.loginconvocatoria.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Tarjeta que contiene la sección
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            // Contenido de la tarjeta
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically // Alinear verticalmente
            ) {
                // Ícono a la izquierda
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Icono del proyecto",
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary // Color del ícono
                )

                Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el ícono y el texto

                // Contenido a la derecha del ícono
                Column {
                    Text(
                        text = "Nombre Proyecto",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Sujeto obligado: Nombre del sujeto aquí",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Descripción del proyecto aquí. Esta es una breve descripción que proporciona más información sobre el proyecto.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Segunda sección (ejemplo)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            // Contenido de la segunda tarjeta
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Otra Sección", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed magna elit, sagittis vel feugiat eu, vehicula et dui. " +
                        "Nulla tempus, arcu et congue porttitor, felis ligula feugiat mi, a euismod neque erat non orci. " +
                        "Aliquam neque odio, malesuada eu placerat non, iaculis cursus nisi. Praesent laoreet enim ac sagittis auctor. " +
                        "Aenean nibh lacus, maximus id imperdiet sit amet, vehicula ut felis. Curabitur commodo non massa at congue. " +
                        "Fusce turpis lacus, sollicitudin ut magna ac, condimentum maximus urna. " +
                        "Sed eleifend consectetur nunc, nec feugiat quam feugiat et..", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
