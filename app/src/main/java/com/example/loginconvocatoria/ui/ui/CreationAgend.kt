package com.example.loginconvocatoria.ui.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun CardComponent(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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

    // Estilo personalizado para el OutlinedTextField
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color(0xFF9C27B0),  // Color morado cuando está enfocado
        disabledLabelColor  = Color.Black,  // Color gris cuando no está enfocado
        disabledBorderColor = Color.Black,  // Color gris cuando está deshabilitado
        disabledTextColor = Color.Black,  // Texto gris cuando está deshabilitado

    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { datePickerDialog.show() },
        readOnly = true,  // Hace que el TextField sea solo lectura
        enabled = false,  // Deshabilita la edición manual del campo
        colors = textFieldColors
    )
}


@Composable
fun DropdownMenuComponent(
    label: String,
    items: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Button(
            onClick = { expanded = !expanded },  // Alterna el estado de expansión
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),  // Mantiene el tamaño al 100% del ancho disponible
            contentPadding = PaddingValues(16.dp),
            shape = RoundedCornerShape(6.dp)  // Bordes redondeados
        ) {
            // Coloca el label en la parte superior del Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = label,  // El label se muestra en la parte superior
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),

                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedOption)  // Muestra la opción seleccionada
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expand Dropdown",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // El DropdownMenu se sigue desplegando cuando el estado 'expanded' es true
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth() // Ajustar el Dropdown al ancho disponible
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CreationAgend(navController: NavController) {
    var NombrePrePro by remember { mutableStateOf("") }
    var FechaPre by remember { mutableStateOf("") }
    var AccionRegu by remember { mutableStateOf("Selecciona una opción") }
    var MateriaSoVaRe by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        CardComponent(title = "Detalles del Proyecto") {
            OutlinedTextField(
                value = NombrePrePro,
                onValueChange = { NombrePrePro = it },
                label = { Text("Nombre del Proyecto") },
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
                // Campo de fecha de presentación
                DateField(
                    label = "Fecha de Presentación",
                    selectedDate = FechaPre,
                    onDateSelected = { FechaPre = it },
                    modifier = Modifier.weight(1f)  // Se ajusta para ocupar el mismo espacio
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Dropdown de acción regulatoria con el Button
                DropdownMenuComponent(
                    label = "Acción Regulatoria",
                    items = listOf("Opción 1", "Opción 2", "Opción 3"),
                    selectedOption = AccionRegu,
                    onOptionSelected = { AccionRegu = it },
                    modifier = Modifier.weight(1f)
                        .padding(top = 9.dp)
                )
            }

            OutlinedTextField(
                value = MateriaSoVaRe,
                onValueChange = { MateriaSoVaRe = it },
                label = { Text("Materia SoVaRe") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}
