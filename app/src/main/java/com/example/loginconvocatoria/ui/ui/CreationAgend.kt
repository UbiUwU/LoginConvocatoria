package com.example.loginconvocatoria.ui.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar // Importación corregida

@Composable
fun DateField(label: String, selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Variables para el año, mes y día
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Mostrar el DatePickerDialog cuando el usuario hace clic en el campo de fecha
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            // Formatear la fecha y actualizar el valor
            val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDateSelected(formattedDate)
        },
        year,
        month,
        day
    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                datePickerDialog.show() // Mostrar el diálogo cuando el campo es clickeado
            },
        enabled = false // Deshabilitado para que no se pueda escribir directamente
    )
}

@Composable
fun CreationAgend(navController: NavController) {
    // Estados para almacenar los valores de cada campo
    var subject by remember { mutableStateOf("") }
    var responsiblePerson by remember { mutableStateOf("") }
    var regulatoryAction by remember { mutableStateOf("") }
    var presentationDate by remember { mutableStateOf("") }
    var preliminaryName by remember { mutableStateOf("") }
    var regulatoryMatter by remember { mutableStateOf("") }
    var actionType by remember { mutableStateOf("") }
    var proposerInfo by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var problemStatement by remember { mutableStateOf("") }
    var justification by remember { mutableStateOf("") }
    var benefits by remember { mutableStateOf("") }
    var legalBasis by remember { mutableStateOf("") }
    var tentativeAirDate by remember { mutableStateOf("") }
    var publicationDate by remember { mutableStateOf("") }
    var preparerInfo by remember { mutableStateOf("") }
    var institutionHead by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Formulario de Propuesta Regulatoria",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                FormField("Sujeto Obligado", subject) { subject = it }
                FormField("Responsable", responsiblePerson) { responsiblePerson = it }
                FormField("Acción regulatoria", regulatoryAction) { regulatoryAction = it }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                DateField("Fecha de presentación", presentationDate) { presentationDate = it }
                FormField("Nombre preliminar", preliminaryName) { preliminaryName = it }
                FormField("Materia de regulación", regulatoryMatter) { regulatoryMatter = it }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                FormField("Tipo de acción", actionType) { actionType = it }
                FormField("Proponente", proposerInfo) { proposerInfo = it }
                FormField("Descripción", description) { description = it }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                FormField("Problema a resolver", problemStatement) { problemStatement = it }
                FormField("Justificación", justification) { justification = it }
                FormField("Beneficios", benefits) { benefits = it }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                FormField("Fundamento jurídico", legalBasis) { legalBasis = it }
                FormField("Fecha AIR", tentativeAirDate) { tentativeAirDate = it }
                FormField("Fecha publicación", publicationDate) { publicationDate = it }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                FormField("Elaborador", preparerInfo) { preparerInfo = it }
                FormField("Titular de la institución", institutionHead) { institutionHead = it }
            }
        }

        Button(
            onClick = {
                // Lógica para guardar los datos del formulario
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Guardar", color = Color.White)
        }
    }
}

@Composable
fun FormField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}
