package com.example.loginconvocatoria.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.R

@Composable
fun DudasInformacion(navController: NavHostController) {
    // Fondo degradado de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.rojo_vino),
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen del encabezado
            Image(
                painter = painterResource(id = R.drawable.duda), // Reemplaza con tu imagen
                contentDescription = "Dudas e información",
                modifier = Modifier
                    .size(160.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto del título
            Text(
                text = "Dudas e información",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Información de contacto
            Text(
                text = "Si tienes dudas o requieres información sobre las agendas regulatorias, comunícate al correo",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Teléfono 1
            Button(
                onClick = {
                    // Acción para llamar al número
                    navController.navigate("tel:7222769600") // Cambia la acción según tu lógica
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.rojo_vino)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "info.cemerqroo@gmail.com", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Horario de atención
            Text(
                text = "Horario de atención:\nlunes a viernes de 9:00 a.m. a 5:00 p.m.",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Segunda sección
            Text(
                text = "Para consultar requisitos \ncomunícate al",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Teléfono 2
            Button(
                onClick = {
                    // Acción para llamar al número
                    navController.navigate("tel:8006969696") // Cambia la acción según tu lógica
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.amarillo)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "984 215 9696", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de disponibilidad
            Text(
                text = "Estamos a tu servicio",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

