package com.example.loginconvocatoria.ui.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.R
import com.example.loginconvocatoria.api.LoginRequest
import com.example.loginconvocatoria.api.LoginResponse
import com.example.loginconvocatoria.api.LoginRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val context = LocalContext.current // Captura el contexto aquí

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    fun performLogin(context: Context) {
        if (email.isBlank()) {
            errorMessage = "El correo electrónico es obligatorio."
            return
        }

        if (password.isBlank()) {
            errorMessage = "La contraseña es obligatoria."
            return
        }

        val loginRequest = LoginRequest(email, password)
        isLoading = true

        LoginRetrofitClient.instance.login(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse>,
                response: retrofit2.Response<LoginResponse>
            ) {
                isLoading = false
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("token", loginResponse?.token)
                        putInt("idUsuario", loginResponse?.usuario?.idUsuario ?: -1)
                        putString("nombreUsuario", loginResponse?.usuario?.nombreUsuario ?: "")
                        putString("email", loginResponse?.usuario?.email ?: "")
                        putString("rol", loginResponse?.usuario?.rol ?: "")
                        apply()
                    }
                    navController.navigate("Dashboard")
                } else {
                    Log.e("Login", "Error en el inicio de sesión: ${response.code()}")
                    errorMessage = "Credenciales incorrectas."
                }
            }

            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                isLoading = false
                Log.e("Login", "Fallo en la conexión: ${t.message}")
                errorMessage = "Error de conexión al servidor."
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cemer_qro),
            contentDescription = "Login image",
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.rojo_vino)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Ingresa tu correo electrónico") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.rojo_vino),
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Ingresa tu contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.rojo_vino),
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                performLogin(context) // Usa el contexto previamente capturado
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.rojo_vino)
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(
                    text = "Login",
                    color = Color.White
                )
            }
        }
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = Color.Red)
        }
    }
}

