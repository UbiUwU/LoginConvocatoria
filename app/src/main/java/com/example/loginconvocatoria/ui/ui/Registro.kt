package com.example.loginconvocatoria.ui.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.R

@Composable
fun Registro(
    navController: NavHostController
) {
    var Nombres by remember { mutableStateOf("") }
    var Apellido1 by remember { mutableStateOf("") }
    var Apellido2 by remember { mutableStateOf("") }
    var Telefono by remember { mutableStateOf("") }
    var Correo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Login image",
            modifier = Modifier.size(200.dp)
        )
        Text(text = "Bienvenido", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Registro: ")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Nombres,
            onValueChange = { Nombres = it },
            label = { Text(text = "Nombres") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Apellido1,
            onValueChange = { Apellido1 = it },
            label = { Text(text = "Primer Apellido") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Apellido2,
            onValueChange = { Apellido2 = it },
            label = { Text(text = "Segundo Apellido") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Telefono,
            onValueChange = { Telefono = it },
            label = { Text(text = "Telefono") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = Correo,
            onValueChange = { Correo = it },
            label = { Text(text = "Correo electrónico") }
        )
        Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
        horizontalArrangement = Arrangement.Center // Centrar los botones horizontalmente
        ) {
        Button(
            onClick = {
                Log.i("Creditar", "Nombres: $Nombres Primer apellido: $Apellido1 Segundo Apellido: $Apellido2 Telefono $Telefono Correo: $Correo")
                navController.navigate("home_screen")
            },
            modifier = Modifier.padding(end = 16.dp) // Añadir espacio entre botones
        ) {
            Text(text = "Registrarse")
        }
        Button(onClick = {
            navController.navigate("Ruta1") // Navegar a PageContent
        }) {
            Text(text = "Iniciar sesión")
        }
    }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Resgistrarte con:")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.fb),
                contentDescription = "Facebook",
                modifier = Modifier
                    .size(60.dp)
                    .clickable { }
            )
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google",
                modifier = Modifier
                    .size(60.dp)
                    .clickable { }
            )
        }
    }
}
