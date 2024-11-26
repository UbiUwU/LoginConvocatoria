package com.example.loginconvocatoria.models

// Respuesta API con el formato correcto
data class ApiResponse(
    val nombreEquipo: String,
    val datosTablas: DatosTablas
)

data class DatosTablas(
    // Comedatos relacionados con las solicitudes de regulación
    val comedatos_mapachesReguladores_solicitud: List<Solicitud>
)

data class Solicitud(
    val id: Int,
    val usuario_id: Int,
    val homoclave_formato: String,
    val nombre_responsable_oficial: String,
    val cargo_responsable_oficial: String,
    val fecha_presentacion: String,
    val nombre_preeliminar: String,
    val materia_regulacion: String,
    val accion_regulatoria: String,
    val nombre_responsable_propuesta: String,
    val cargo_responsable_propuesta: String,
    val descripcion_propuesta: String,
    val problematica_propuesta: String,
    val justificacion_propuesta: String,
    val beneficio_propuesta: String,
    val fundamento_juridico: String,
    val fecha_tentativa_presentacion: String,
    val fecha_tentativa_publicacion: String,
    val nombre_responsable_elabora: String,
    val cargo_responsable_elabora: String,
    val nombre_titular: String,
    val cargo_titular: String,
    val publicacion: String
)