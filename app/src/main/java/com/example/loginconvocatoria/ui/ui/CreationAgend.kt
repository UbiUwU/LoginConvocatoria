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
import java.util.Calendar

@Composable
fun CardComponent(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start // Cambia la alineación a la izquierda
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp) // Espaciado entre el título y el contenido
            )
            content()
        }
    }
}

@Composable
fun DateField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Crear el DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDateSelected(formattedDate)
        },
        year,
        month,
        day
    )

    // OutlinedTextField que no muestra el teclado
    OutlinedTextField(
        value = selectedDate,
        onValueChange = {}, // No se permite cambiar el texto directamente
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { datePickerDialog.show() }, // Mostrar el DatePicker al hacer clic
        readOnly = true, // Esto evitará que aparezca el teclado
        enabled = false
    )
}


@Composable
fun CreationAgend(navController: NavController) {
    var NombrePrePro by remember { mutableStateOf("") }
    var FechaPre by remember { mutableStateOf("") }
    var AccionRegu by remember { mutableStateOf("") }
    var MateriaSoVaRe by remember { mutableStateOf("") }
    var DescripcionProRe by remember { mutableStateOf("") }
    var ProblematicaResol by remember { mutableStateOf("") }
    var Justificacion by remember { mutableStateOf("") }
    var Beneficios by remember { mutableStateOf("") }
    var FundamentosJurid by remember { mutableStateOf("") }
    var FechaTentaAIR by remember { mutableStateOf("") }
    var FechaTentaPOE by remember { mutableStateOf("") }
    var SujetoObli by remember { mutableStateOf("") }
    var ResponsableElab by remember { mutableStateOf("") }
    var ResponsableElabInfo by remember { mutableStateOf("") }
    var ResponsableInsti by remember { mutableStateOf("") }
    var ResponsableQuienE by remember { mutableStateOf("") }
    var FechaSioNo by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        CardComponent(title = "Detalles del Proyecto") {
            // Nombre del Proyecto - Parte superior centrado
            OutlinedTextField(
                value = NombrePrePro,
                onValueChange = { NombrePrePro = it },
                label = { Text("Nombre del Proyecto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Fila central con FechaPre y AccionRegu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateField(
                    label = "Fecha de Presentación",
                    selectedDate = FechaPre,
                    onDateSelected = { FechaPre = it },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = AccionRegu,
                    onValueChange = { AccionRegu = it },
                    label = { Text("Acción Regulatoria") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Materia SoVaRe - Parte inferior centrado
            OutlinedTextField(
                value = MateriaSoVaRe,
                onValueChange = { MateriaSoVaRe = it },
                label = { Text("Materia SoVaRe") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }

        CardComponent(title = "Datos de explicacion") {
            OutlinedTextField(
                value = DescripcionProRe,
                onValueChange = { DescripcionProRe = it },
                label = { Text("Descripción de la agenda") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = ProblematicaResol,
                onValueChange = { ProblematicaResol = it },
                label = { Text("Problema que se pretende resolver") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = Justificacion,
                onValueChange = { Justificacion = it },
                label = { Text("Justificación para emitir") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = Beneficios,
                onValueChange = { Beneficios = it },
                label = { Text("Beneficios que generará") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = FundamentosJurid,
                onValueChange = { FundamentosJurid = it },
                label = { Text("Fundamentos jurídicos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
        // Nueva Card para información adicional
        CardComponent(title = "Fechas") {
            // Nombre del Proyecto - Parte superior centrado
            OutlinedTextField(
                value = FechaSioNo,
                onValueChange = { FechaSioNo = it },
                label = { Text("Fechas de si o no ....................") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateField(
                    label = "Fecha de Presentación para la AIR",
                    selectedDate = FechaTentaAIR,
                    onDateSelected = { FechaTentaAIR = it },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                DateField(
                    label = "Fecha de Presentación para el periodico",
                    selectedDate = FechaTentaPOE,
                    onDateSelected = { FechaTentaPOE = it },
                    modifier = Modifier.weight(1f)
                )

            }

        }
        // Nueva Card para información adicional
        CardComponent(title = "Información Adicional") {
            OutlinedTextField(
                value = SujetoObli,
                onValueChange = { SujetoObli = it },
                label = { Text("Sujeto obligado") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = ResponsableElab,
                onValueChange = { ResponsableElab = it },
                label = { Text("Responsable cargo de la persona responsable oficial de mejora regulatoria.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = ResponsableElabInfo,
                onValueChange = { ResponsableElabInfo = it },
                label = { Text("Responsable cargo de la persona responsable oficial de mejora regulatoria.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = ResponsableInsti,
                onValueChange = { ResponsableInsti = it },
                label = { Text("Responsable cargo de la persona responsable oficial de mejora regulatoria.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = ResponsableQuienE,
                onValueChange = { ResponsableQuienE = it },
                label = { Text("Responsable cargo de la persona responsable cargo de quien elaboró.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}
