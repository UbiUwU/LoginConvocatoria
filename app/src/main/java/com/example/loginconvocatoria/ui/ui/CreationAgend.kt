@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.loginconvocatoria.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.*
import com.example.loginconvocatoria.R
import com.example.loginconvocatoria.api.LoginRetrofitClient
import com.example.loginconvocatoria.models.SolicitudAgenda

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CreateSolicitudScreen(navController: NavController) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    // Estados para todos los campos del formulario
    var usuarioId by remember { mutableStateOf(TextFieldValue("")) }
    var homoclaveFormato by remember { mutableStateOf(TextFieldValue("")) }
    var nombreResponsableOficial by remember { mutableStateOf(TextFieldValue("")) }
    var cargoResponsableOficial by remember { mutableStateOf(TextFieldValue("")) }
    var fechaPresentacion by remember { mutableStateOf(TextFieldValue("")) }
    var nombrePreeliminar by remember { mutableStateOf(TextFieldValue("")) }
    var materiaRegulacion by remember { mutableStateOf(TextFieldValue("")) }
    var accionRegulatoria by remember { mutableStateOf(TextFieldValue("")) }
    var nombreResponsablePropuesta by remember { mutableStateOf(TextFieldValue("")) }
    var cargoResponsablePropuesta by remember { mutableStateOf(TextFieldValue("")) }
    var descripcionPropuesta by remember { mutableStateOf(TextFieldValue("")) }
    var problematicaPropuesta by remember { mutableStateOf(TextFieldValue("")) }
    var justificacionPropuesta by remember { mutableStateOf(TextFieldValue("")) }
    var beneficioPropuesta by remember { mutableStateOf(TextFieldValue("")) }
    var fundamentoJuridico by remember { mutableStateOf(TextFieldValue("")) }
    var fechaTentativaPresentacion by remember { mutableStateOf(TextFieldValue("")) }
    var fechaTentativaPublicacion by remember { mutableStateOf(TextFieldValue("")) }
    var nombreResponsableElabora by remember { mutableStateOf(TextFieldValue("")) }
    var cargoResponsableElabora by remember { mutableStateOf(TextFieldValue("")) }
    var nombreTitular by remember { mutableStateOf(TextFieldValue("")) }
    var cargoTitular by remember { mutableStateOf(TextFieldValue("")) }
    var publicacion by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear Nueva Solicitud de Agenda",
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(id = R.color.rojo_vino)
        )

        // Campos del formulario
        FormField("ID del Usuario", usuarioId) { usuarioId = it }
        FormField("Homoclave Formato", homoclaveFormato) { homoclaveFormato = it }
        FormField("Nombre Responsable Oficial", nombreResponsableOficial) { nombreResponsableOficial = it }
        FormField("Cargo Responsable Oficial", cargoResponsableOficial) { cargoResponsableOficial = it }
        FormField("Fecha de Presentación (YYYY-MM-DD)", fechaPresentacion) { fechaPresentacion = it }
        FormField("Nombre Preliminar", nombrePreeliminar) { nombrePreeliminar = it }
        FormField("Materia de Regulación", materiaRegulacion) { materiaRegulacion = it }
        FormField("Acción Regulatoria", accionRegulatoria) { accionRegulatoria = it }
        FormField("Nombre Responsable Propuesta", nombreResponsablePropuesta) { nombreResponsablePropuesta = it }
        FormField("Cargo Responsable Propuesta", cargoResponsablePropuesta) { cargoResponsablePropuesta = it }
        FormField("Descripción Propuesta", descripcionPropuesta) { descripcionPropuesta = it }
        FormField("Problema Propuesta", problematicaPropuesta) { problematicaPropuesta = it }
        FormField("Justificación Propuesta", justificacionPropuesta) { justificacionPropuesta = it }
        FormField("Beneficio Propuesta", beneficioPropuesta) { beneficioPropuesta = it }
        FormField("Fundamento Jurídico", fundamentoJuridico) { fundamentoJuridico = it }
        FormField("Fecha Tentativa Presentación", fechaTentativaPresentacion) { fechaTentativaPresentacion = it }
        FormField("Fecha Tentativa Publicación", fechaTentativaPublicacion) { fechaTentativaPublicacion = it }
        FormField("Nombre Responsable Elabora", nombreResponsableElabora) { nombreResponsableElabora = it }
        FormField("Cargo Responsable Elabora", cargoResponsableElabora) { cargoResponsableElabora = it }
        FormField("Nombre Titular", nombreTitular) { nombreTitular = it }
        FormField("Cargo Titular", cargoTitular) { cargoTitular = it }

        // Botón para guardar la solicitud
        Button(
            onClick = {
                if (areFieldsValid(
                        usuarioId.text, homoclaveFormato.text, nombreResponsableOficial.text,
                        cargoResponsableOficial.text, fechaPresentacion.text
                    )
                ) {
                    isLoading = true
                    enviarSolicitud(
                        solicitud = SolicitudAgenda(
                            usuario_id = usuarioId.text.toInt(),
                            homoclave_formato = homoclaveFormato.text,
                            nombre_responsable_oficial = nombreResponsableOficial.text,
                            cargo_responsable_oficial = cargoResponsableOficial.text,
                            fecha_presentacion = fechaPresentacion.text,
                            nombre_preeliminar = nombrePreeliminar.text,
                            materia_regulacion = materiaRegulacion.text,
                            accion_regulatoria = accionRegulatoria.text,
                            nombre_responsable_propuesta = nombreResponsablePropuesta.text,
                            cargo_responsable_propuesta = cargoResponsablePropuesta.text,
                            descripcion_propuesta = descripcionPropuesta.text,
                            problematica_propuesta = problematicaPropuesta.text,
                            justificacion_propuesta = justificacionPropuesta.text,
                            beneficio_propuesta = beneficioPropuesta.text,
                            fundamento_juridico = fundamentoJuridico.text,
                            fecha_tentativa_presentacion = fechaTentativaPresentacion.text,
                            fecha_tentativa_publicacion = fechaTentativaPublicacion.text,
                            nombre_responsable_elabora = nombreResponsableElabora.text,
                            cargo_responsable_elabora = cargoResponsableElabora.text,
                            nombre_titular = nombreTitular.text,
                            cargo_titular = cargoTitular.text,
                            publicacion = publicacion
                        ),
                        context = context,
                        onSuccess = { navController.navigate("success_screen") },
                        onError = { errorMessage ->
                            isLoading = false
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                } else {
                    Toast.makeText(context, "Por favor, completa los campos obligatorios.", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = colorResource(id = R.color.white))
            } else {
                Text("Guardar")
            }
        }
    }
}



@Composable
fun FormField(label: String, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.rojo_vino),
            unfocusedBorderColor = colorResource(id = R.color.light_gray),
            cursorColor = colorResource(id = R.color.rojo_vino)
        )
    )
}



// Validar campos requeridos
fun areFieldsValid(vararg fields: String): Boolean = fields.all { it.isNotEmpty() }

// Función para enviar la solicitud al backend
fun enviarSolicitud(
    solicitud: SolicitudAgenda,
    context: android.content.Context,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val sharedPreferences = context.getSharedPreferences("APP_PREF", android.content.Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("TOKEN", "") ?: ""

    val call = LoginRetrofitClient.instance.createSolicitud("Bearer $token", solicitud)
    call.enqueue(object : Callback<SolicitudAgenda> {
        override fun onResponse(call: Call<SolicitudAgenda>, response: Response<SolicitudAgenda>) {
            if (response.isSuccessful) {
                onSuccess()
            } else {
                val errorBody = response.errorBody()?.string()
                onError("Error: ${response.code()} - $errorBody")
            }
        }

        override fun onFailure(call: Call<SolicitudAgenda>, t: Throwable) {
            onError("Error: ${t.message}")
        }
    })
}