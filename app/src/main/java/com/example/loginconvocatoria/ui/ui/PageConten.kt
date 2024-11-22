package com.example.loginconvocatoria.ui.ui

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
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Send
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageContent() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    // Controlador de estado del cuadro de comentarios
    var showComments by remember { mutableStateOf(false) }

    // Contenido principal de la página
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
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
            details = "Detalles de la propuesta:",
            detailsContent = "Lorem ipsum dolor sit amet...",
            problem = "Problema que se pretende resolver con la propuesta:",
            problemContent = "Lorem ipsum dolor sit amet...",
            justification = "Justificación para emitir:",
            justificationContent = "Lorem ipsum dolor sit amet...",
            benefits = "Beneficios que generará:",
            benefitsContent = "Lorem ipsum dolor sit amet...",
            legalBasis = "Fundamentos jurídicos:",
            legalBasisContent = "Lorem ipsum dolor sit amet..."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para mostrar el cuadro de comentarios
        Button(
            onClick = { coroutineScope.launch { sheetState.show() } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ver comentarios")
        }
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
                Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Text(text = subject, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.secondary)
                Text(text = description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}

@Composable
fun AgendaInfoCard(title: String, action: String, responsiblePerson: String, responsibleInfo: String, responsableElab: String,
                   responsableElabInfo: String, titularInsti: String, titularInstiInfo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = action, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
            Text(text = responsiblePerson, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Text(text = responsibleInfo, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text(text = responsableElab, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Text(text = responsableElabInfo, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text(text = titularInsti, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Text(text = titularInstiInfo, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}

@Composable
fun ProposalDetailsCard(
    title: String, details: String, detailsContent: String,
    problem: String, problemContent: String,
    justification: String, justificationContent: String,
    benefits: String, benefitsContent: String,
    legalBasis: String, legalBasisContent: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))

            DetailSection(header = details, content = detailsContent)
            DetailSection(header = problem, content = problemContent)
            DetailSection(header = justification, content = justificationContent)
            DetailSection(header = benefits, content = benefitsContent)
            DetailSection(header = legalBasis, content = legalBasisContent)
        }
    }
}

@Composable
fun DetailSection(header: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = header, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
        Text(text = content, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}
