package com.example.loginconvocatoria.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Send
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.loginconvocatoria.R


@Composable
fun DropdownMenuComponentP(
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
@Composable
fun PageContent() {
    // Variable temporal para determinar el rol del usuario
    val userRole = "admin" // Cambia a "admin" según la necesidad

    when (userRole) {
        "client" -> ClientScreen()
        "admin" -> AdminScreen()
        else -> Text("Rol no reconocido", style = MaterialTheme.typography.bodyLarge)
    }
}


// Función para mostrar cuadros de texto (compartida entre ambos roles)
@Composable
fun CommonTextFields() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProjectCard(
            title = "Nombre Proyecto",
            subject = "Sujeto obligado: Nombre del sujeto aquí",
            description = "Descripción del proyecto aquí. Esta es una breve descripción que proporciona más información sobre el proyecto."
        )

        AgendaInfoCard(
            title = "Información general de la agenda",
            action = "Acción regulatoria: A) Emitir. La creación de una nueva agenda",
            responsiblePerson = "Nombre y cargo de la persona responsable de la propuesta:",
            responsibleInfo = "M. en C. Karla Morales Valenzuela. Directora de especies menores",
            responsableElab = "Nombre y cargo de la persona responsable de la elaboración de la propuesta:",
            responsableElabInfo = "M. en C. Karla Morales Valenzuela. Directora de especies menores",
            titularInsti = "Nombre y cargo titular de la institución:",
            titularInstiInfo = "M. en C. Karla Morales Valenzuela. Directora de especies menores",
        )

        ProposalDetailsCard(
            title = "Información sobre la agenda",
            problem = "Problema que se pretende resolver con la propuesta:",
            problemContent = "Lorem ipsum dolor sit amet...",
            justification = "Justificación para emitir:",
            justificationContent = "Lorem ipsum dolor sit amet...",
            benefits = "Beneficios que generará:",
            benefitsContent = "Lorem ipsum dolor sit amet...",
            legalBasis = "Fundamentos jurídicos:",
            legalBasisContent = "Lorem ipsum dolor sit amet..."
        )
    }
}

// Pantalla para clientes
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        CommonTextFields() // Contenido común para ambos roles

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para mostrar comentarios
        Button(
            onClick = { coroutineScope.launch { sheetState.show() } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.amarillo), // Fondo del botón
                contentColor = colorResource(id = R.color.white) // Color del texto
            )
        ) {
            Text(text = "Ver comentarios")
        }

        // Cuadro deslizante de comentarios
        if (sheetState.isVisible) {
            ModalBottomSheet(
                onDismissRequest = { coroutineScope.launch { sheetState.hide() } },
                sheetState = sheetState
            ) {
                CommentsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen() {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }
    val optionsList = listOf("Opción 1", "Opción 2", "Opción 3")

    // Estado para la opción seleccionada
    var Acción_Regulatoria by remember { mutableStateOf(optionsList[0]) }
    var comment by remember { mutableStateOf("") } // Estado para el comentario

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        CommonTextFields() // Contenido común para ambos roles

        Spacer(modifier = Modifier.height(16.dp))
        // Botón "Enviar"
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.amarillo), // Fondo del botón
                    contentColor = colorResource(id = R.color.white) // Color del texto
                )
            ) {
                Text(text = "Acción Regulatoria")
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Seleccione la Acción Regulatoria") },
                text = {
                    Column {
                        // Aquí se agregan el DropdownMenu y el campo de texto para comentarios
                        DropdownMenuComponentP(
                            label = "Materia de regulación",
                            items = optionsList,
                            selectedOption = Acción_Regulatoria,
                            onOptionSelected = { Acción_Regulatoria = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        // Caja de texto para el comentario
                        OutlinedTextField(
                            value = comment,
                            onValueChange = { comment = it },
                            label = { Text("Escribe un comentario") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            singleLine = false,
                            textStyle = TextStyle(
                                color = colorResource(id = R.color.black), // Color del texto ingresado
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorResource(id = R.color.amarillo), // Color del borde cuando está enfocado
                                unfocusedBorderColor = colorResource(id = R.color.black), // Color del borde cuando no está enfocado
                                cursorColor = colorResource(id = R.color.black), // Color del cursor
                                focusedLabelColor = colorResource(id = R.color.black), // Color de la etiqueta cuando está enfocado
                                unfocusedLabelColor = colorResource(id = R.color.black), // Color de la etiqueta cuando no está enfocado

                            )
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            // Acción para "Sí"
                            showDialog = false
                        }
                    ) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            // Acción para "No"
                            showDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.amarillo),
                            contentColor = colorResource(id = R.color.white)
                        )
                    ) {
                        Text("No")
                    }
                },
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}






// Contenido del cuadro de comentarios
@Composable
fun CommentsSection(comments: List<Pair<String, String>>, onAddComment: (String) -> Unit) {
    var newComment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Comentarios",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        // Campo para escribir un comentario
        OutlinedTextField(
            value = newComment,
            onValueChange = { newComment = it },
            label = { Text("Escribe un comentario") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {
                    if (newComment.isNotBlank()) {
                        onAddComment(newComment) // Agregar comentario
                        newComment = "" // Limpiar el campo
                    }
                }) {
                    Icon(Icons.Outlined.Send, contentDescription = "Enviar comentario")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Opciones de filtrado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { /* Filtrar comentarios recientes */ }) {
                Text("Recientes")
            }
            TextButton(onClick = { /* Filtrar comentarios populares */ }) {
                Text("Populares")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Lista de comentarios
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(comments) { (name, comment) ->
                CommentItem(commentText = comment, userName = name)
            }
        }
    }
}


@Composable
fun CommentsScreen() {
    // Lista mutable de comentarios (inicializada con comentarios genéricos)
    val comments = remember {
        mutableStateListOf(
            Pair("Usuario Anónimo (21/11/2024 10:00:00)", "Me parece muy interesante esta propuesta."),
            Pair("Usuario Anónimo (21/11/2024 10:15:00)", "¿Podrían dar más detalles sobre los beneficios?"),
            Pair("Usuario Anónimo (21/11/2024 10:30:00)", "Estoy de acuerdo con los puntos presentados."),
            Pair("Usuario Anónimo (21/11/2024 11:00:00)", "¿Habrá reuniones adicionales para discutir este tema?"),
            Pair("Usuario Anónimo (21/11/2024 11:15:00)", "No estoy seguro de entender la justificación."),
            Pair("Usuario Anónimo (21/11/2024 11:45:00)", "Gracias por la información."),
            Pair("Usuario Anónimo (21/11/2024 12:00:00)", "Sugiero agregar un ejemplo práctico en la propuesta."),
            Pair("Usuario Anónimo (21/11/2024 12:30:00)", "¡Excelente trabajo del equipo responsable!"),
            Pair("Usuario Anónimo (21/11/2024 12:45:00)", "¿Cuándo estará disponible la versión final?"),
            Pair("Usuario Anónimo (21/11/2024 13:00:00)", "Este tema es muy importante. Espero más información.")
        )
    }

    // Llamada al componente de sección de comentarios con funcionalidad para agregar
    CommentsSection(
        comments = comments,
        onAddComment = { comment ->
            // Obtener la fecha y hora actuales
            val timestamp = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault())
                .format(java.util.Date())
            // Agregar el nuevo comentario a la lista
            comments.add(Pair("Usuario Anónimo ($timestamp)", comment))
        }
    )
}


//---------------------------------------------------//
@Composable
fun CommentItem(commentText: String, userName: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto de usuario representada por el ícono AccountCircle
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Foto de usuario",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            // Texto del comentario
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = commentText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
fun ProjectCard(title: String, subject: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.rojo_vino)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Icono del proyecto",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = title, style = MaterialTheme.typography.titleLarge, color = colorResource(id = R.color.white))
                Text(text = subject, style = MaterialTheme.typography.titleSmall,color = colorResource(id = R.color.white))
                Text(text = description, style = MaterialTheme.typography.bodyMedium, color = colorResource(id = R.color.white))
            }
        }
    }
}

@Composable
fun AgendaInfoCard(title: String, action: String, responsiblePerson: String, responsibleInfo: String, responsableElab: String,
                    responsableElabInfo: String, titularInsti: String, titularInstiInfo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.rojo_vino)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = colorResource(id = R.color.white))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = action, style = MaterialTheme.typography.bodyLarge, color = colorResource(id = R.color.white))
            Text(text = responsiblePerson, style = MaterialTheme.typography.titleSmall, color = colorResource(id = R.color.white))
            Text(text = responsibleInfo, style = MaterialTheme.typography.bodyMedium, color = colorResource(id = R.color.white))
            Text(text = responsableElab, style = MaterialTheme.typography.titleSmall, color = colorResource(id = R.color.white))
            Text(text = responsableElabInfo, style = MaterialTheme.typography.bodyMedium, color = colorResource(id = R.color.white))
            Text(text = titularInsti, style = MaterialTheme.typography.titleSmall, color = colorResource(id = R.color.white))
            Text(text = titularInstiInfo, style = MaterialTheme.typography.bodyMedium, color = colorResource(id = R.color.white))
        }
    }
}

@Composable
fun ProposalDetailsCard(
    title: String,
    problem: String, problemContent: String,
    justification: String, justificationContent: String,
    benefits: String, benefitsContent: String,
    legalBasis: String, legalBasisContent: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.rojo_vino)
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = colorResource(id = R.color.white))
            Spacer(modifier = Modifier.height(8.dp))
            DetailSectionV(header = problem, content = problemContent)
            DetailSectionV(header = justification, content = justificationContent)
            DetailSectionV(header = benefits, content = benefitsContent)
            DetailSectionV(header = legalBasis, content = legalBasisContent)
        }
    }
}

@Composable
fun DetailSection(header: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = header, style = MaterialTheme.typography.titleSmall, color = colorResource(id = R.color.white))
        Text(text = content, style = MaterialTheme.typography.bodyMedium, color = colorResource(id = R.color.white))
    }
}