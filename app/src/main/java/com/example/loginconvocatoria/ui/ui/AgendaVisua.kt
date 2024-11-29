package com.example.loginconvocatoria.ui.ui

import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaVisua(
    navController: NavHostController,
    nombreProyecto: String,
    fechaPre: String,
    accionRegu: String,
    materiaSoVaRe: String,
    descripcion: String,
    problematicaResol: String,
    justificacion: String,
    beneficios: String,
    fundamentosJurid: String,
    fechaTentaAIR: String,
    fechaTentaPOE: String,
    sujetoObli: String,
    responsableElab: String,
    responsableElabInfo: String,
    responsableInsti: String,
    responsableQuienE: String,
    fechaSioNo: String,


    ) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    // Controlador de estado del cuadro de comentarios
    var showComments by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    // Contenido principal de la página
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        ProjectCardV(
            title = "Nombre Proyecto: $nombreProyecto",
            subject = "Sujeto obligado: $sujetoObli",
            description = "Descripción del proyecto: $descripcion"
        )

        AgendaInfoCardV(
            title = "Información general de la agenda",
            action = "Materia de regulación: $materiaSoVaRe",
            responsiblePerson = "Nombre y cargo de la persona responsable de la propuesta:",
            responsibleInfo = "$responsableElabInfo",
            responsableElab = "Nombre y cargo de la persona responsable de la elaboración de la propuesta:",
            responsableElabInfo = "$responsableQuienE",
            titularInsti = "Nombre y cargo titular de la institución:",
            titularInstiInfo = "$responsableInsti",
        )

        ProposalDetailsCardV(
            title = "Información sobre la agenda",
            problem = "Problema que se pretende resolver con la propuesta:",
            problemContent = "$problematicaResol",
            justification = "Justificación para emitir:",
            justificationContent = "$justificacion",
            benefits = "Beneficios que generará:",
            benefitsContent = "$beneficios",
            legalBasis = "Fundamentos jurídicos:",
            legalBasisContent = "$fundamentosJurid"
        )

        Spacer(modifier = Modifier.height(1.dp))
        // Botón "Enviar"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.amarillo), // Fondo del botón
                    contentColor = colorResource(id = R.color.white) // Color del texto
                )
            ) {
                Text(text = "Enviar")
            }
        }

        // Cuadro de diálogo
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Confirmación") },
                text = { Text(text = "¿Quiere enviar la agenda?") },
                confirmButton = {
                    Button(
                        onClick = {
                            // Acción para "Sí"
                            showDialog = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.amarillo), // Fondo del botón
                            contentColor = colorResource(id = R.color.white) // Color del texto
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

@Composable
fun ProjectCardV(title: String, subject: String, description: String) {
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
fun AgendaInfoCardV(title: String, action: String, responsiblePerson: String, responsibleInfo: String, responsableElab: String,
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
fun ProposalDetailsCardV(
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
fun DetailSectionV(header: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = header, style = MaterialTheme.typography.titleSmall, color = colorResource(id = R.color.white))
        Text(text = content, style = MaterialTheme.typography.bodyMedium, color = colorResource(id = R.color.white))
    }
}
