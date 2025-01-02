package com.example.loginconvocatoria.models
//YA NO SE USA
data class CrearSolicitudRequest(
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
    val publicacion: Boolean
)

// Respuesta del servidor
data class CrearSolicitudResponse(
    val message: String,
    val solicitud: Solicitud
)

