@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.loginconvocatoria.ui.ui

import android.app.DatePickerDialog
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.loginconvocatoria.R

@Composable
fun CardComponent(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
             colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.rojo_vino)  // Fondo de la tarjeta
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            content()
        }
    }
}
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        textStyle = TextStyle(
            color = colorResource(id = R.color.white), // Color del texto ingresado
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.amarillo), // Color del borde cuando está enfocado
            unfocusedBorderColor = colorResource(id = R.color.white), // Color del borde cuando no está enfocado
            cursorColor = colorResource(id = R.color.white), // Color del cursor
            focusedLabelColor = colorResource(id = R.color.white), // Color de la etiqueta cuando está enfocado
            unfocusedLabelColor = colorResource(id = R.color.white), // Color de la etiqueta cuando no está enfocado

        )
    )
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
            shape = RoundedCornerShape(6.dp),  // Bordes redondeados
            colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.amarillo),  // Fondo
            contentColor = Color.White  // Color del texto
            )
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
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp) // Ajustar tamaño del ícono
                            .graphicsLayer { rotationZ = if (expanded) 90f else 0f } // Rotar el ícono según el estado
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
                .background(Color.Transparent) // Cambiar el fondo del menú
        ) {
            items.forEachIndexed { index, item ->
                val shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp) // Primer item con bordes redondeados en la parte superior
                    items.size - 1 -> RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp) // Último item con bordes redondeados en la parte inferior
                    else -> RoundedCornerShape(0.dp) // Items intermedios sin bordes redondeados
                }
                DropdownMenuItem(
                    text = { Text(item,
                        color =  colorResource(id = R.color.white)) },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    },
                    modifier = Modifier

                        .background(colorResource(id = R.color.amarillo), shape = shape) // Aplica el shape según la posición
                        .border(2.dp, Color.White, shape) // Borde blanco con la misma forma
                        .fillMaxWidth() // Ocupar todo el ancho disponible
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationAgend(navController: NavController) {
    var NombrePrePro by remember { mutableStateOf("") }
    var FechaPre by remember { mutableStateOf("") }
    var AccionRegu by remember { mutableStateOf("Selecciona una opción") }


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


    // Define la lista de opciones de manera separada
    val optionsList = listOf("Opción 1", "Opción 2", "Opción 3")

    // Estado para la opción seleccionada
    var MateriaSoVaRe by remember { mutableStateOf(optionsList[0]); mutableStateOf("Selecciona una opción") }

    var currentSection by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Mostrar el contenido basado en la sección actual
        when (currentSection) {
            1 -> {
                CardComponent(title = "Detalles de la agenda") {
                    OutlinedTextField(
                        value = NombrePrePro,
                        onValueChange = { NombrePrePro = it },
                        label = { Text("Nombre del Proyecto")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(
                            color = colorResource(id = R.color.white), // Color del texto ingresado
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(id = R.color.amarillo), // Color del borde cuando está enfocado
                            unfocusedBorderColor = colorResource(id = R.color.white), // Color del borde cuando no está enfocado
                            cursorColor = colorResource(id = R.color.white), // Color del cursor
                            focusedLabelColor = colorResource(id = R.color.white), // Color de la etiqueta cuando está enfocado
                            unfocusedLabelColor = colorResource(id = R.color.white), // Color de la etiqueta cuando no está enfocado

                        )
                    )
                    // Dropdown de acción regulatoria con el Button
                    DropdownMenuComponent(
                        label = "Materia de regulación",
                        items = optionsList,
                        selectedOption = MateriaSoVaRe,
                        onOptionSelected = { MateriaSoVaRe = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }
            }

            2 -> {
                CardComponent(title = "Datos de explicacion") {
                    CustomOutlinedTextField(
                        value = DescripcionProRe,
                        onValueChange = { DescripcionProRe = it },
                        label = "Descripción de la agenda",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    CustomOutlinedTextField(
                        value = ProblematicaResol,
                        onValueChange = { ProblematicaResol = it },
                        label = "Problema que se pretende resolver",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = Justificacion,
                        onValueChange = { Justificacion = it },
                        label ="Justificación para emitir",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = Beneficios,
                        onValueChange = { Beneficios = it },
                        label = "Beneficios que generará",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = FundamentosJurid,
                        onValueChange = { FundamentosJurid = it },
                        label = "Fundamentos jurídicos",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }

            3 -> {
                // Nueva Card para información adicional
                CardComponent(title = "Información Adicional") {
                    CustomOutlinedTextField(
                        value = SujetoObli,
                        onValueChange = { SujetoObli = it },
                        label = "Sujeto obligado",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = ResponsableElab,
                        onValueChange = { ResponsableElab = it },
                        label = "Responsable cargo de la persona responsable oficial de mejora regulatoria.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = ResponsableElabInfo,
                        onValueChange = { ResponsableElabInfo = it },
                        label = "Responsable cargo de la persona responsable oficial de mejora regulatoria.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = ResponsableInsti,
                        onValueChange = { ResponsableInsti = it },
                        label = "Responsable cargo de la persona responsable oficial de mejora regulatoria.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    CustomOutlinedTextField(
                        value = ResponsableQuienE,
                        onValueChange = { ResponsableQuienE = it },
                        label = "Responsable cargo de la persona responsable cargo de quien elaboró.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }

        // Botones de navegación
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón "Anterior" (desactivado si está en la primera sección)
            Button(
                onClick = { currentSection -= 1 },
                enabled = currentSection > 1, //  Solo habilitado si no está en la última sección
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.amarillo), // Fondo del botón
                    contentColor = colorResource(id = R.color.white) // Color del texto
                )
            ) {
                Text("Anterior")
            }

            // Botón "Enviar" (solo visible en la última sección)
            if (currentSection == 3) {
                Button(
                    onClick = {
                        val encodedUrl = "${Uri.encode(NombrePrePro)}/${Uri.encode(FechaPre)}/${Uri.encode(AccionRegu)}/${Uri.encode(MateriaSoVaRe)}/" +
                                "${Uri.encode(DescripcionProRe)}/${Uri.encode(ProblematicaResol)}/${Uri.encode(Justificacion)}/${Uri.encode(Beneficios)}/" +
                                "${Uri.encode(FundamentosJurid)}/${Uri.encode(FechaTentaAIR)}/${Uri.encode(FechaTentaPOE)}/${Uri.encode(SujetoObli)}/" +
                                "${Uri.encode(ResponsableElab)}/${Uri.encode(ResponsableElabInfo)}/${Uri.encode(ResponsableInsti)}/${Uri.encode(ResponsableQuienE)}/" +
                                "${Uri.encode(FechaSioNo)}"
                        // Navegar con la URL codificada
                        navController.navigate("Agenda_Visua/$encodedUrl")
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text("Enviar")
                }
            }

            // Botón "Siguiente" (desactivado si está en la última sección)
            Button(
                onClick = { currentSection += 1 },
                enabled = currentSection < 3, // Solo habilitado si no está en la última sección
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.amarillo), // Fondo del botón
                    contentColor = colorResource(id = R.color.white) // Color del texto
                )
            ) {
                Text("Siguiente")
            }
        }
    }
}