package com.example.loginconvocatoria.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginconvocatoria.R
import com.example.loginconvocatoria.models.UserProfile

fun getUserProfile(context: Context): UserProfile {
    val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    return UserProfile(
        idUsuario = sharedPref.getInt("idUsuario", -1),
        nombreUsuario = sharedPref.getString("nombreUsuario", "") ?: "",
        email = sharedPref.getString("email", "") ?: "",
        rol = sharedPref.getString("rol", "") ?: ""
    )
}

fun logout(context: Context) {
    val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        clear()
        apply()
    }
}

@Composable
fun PerfilUsuarioScreen(context: Context) {
    val userProfile = remember { getUserProfile(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFD7C2), // Naranja pastel
                        Color(0xFFFFF7E6)  // Beige claro
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Espacio superior
        Spacer(modifier = Modifier.height(32.dp))

        // Contenedor de la imagen de perfil mejorado
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(140.dp)
        ) {
            // Borde decorativo circular con degradado
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFFFD7C2), Color.White),
                            center = Offset(0.5f, 0.5f),
                            radius = 200f
                        ),
                        shape = CircleShape
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.usuario_foto),
                contentDescription = "Imagen de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .shadow(8.dp, CircleShape)
                    .background(Color.White, shape = CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Título de sección
        Text(
            text = "Perfil de Usuario",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A4A4A)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Tarjeta con datos del usuario mejorada
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileRow("Nombre", userProfile.nombreUsuario, R.drawable.person)
                ProfileRow("Correo", userProfile.email, R.drawable.email)
                ProfileRow("Yo soy un", userProfile.rol, R.drawable.rol)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Botón de cerrar sesión mejorado
        Button(
            onClick = {
                logout(context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB22222), // Rojo vibrante
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(10.dp)
        ) {
            Text(text = "Cerrar Sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String, iconRes: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = "$label Icono",
            tint = Color(0xFF8B0000), // Rojo oscuro
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label: $value",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4A4A4A)
        )
    }
}
