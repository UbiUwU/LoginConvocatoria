package com.example.loginconvocatoria.models

import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonDeserializationContext
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type


data class SolicitudAgenda(
   // val id: Int? = null, // ID opcional, ya que el servidor lo asignará
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
    @JsonAdapter(BooleanAdapter::class) // Usa el adaptador para convertir números a booleanos
    val publicacion: Boolean
)


class BooleanAdapter : JsonDeserializer<Boolean>, JsonSerializer<Boolean> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Boolean {
        return when (val value = json?.asJsonPrimitive) {
            null -> false
            else -> {
                try {
                    value.asBoolean
                } catch (e: Exception) {
                    value.asInt != 0 // Convierte 0/1 en booleano
                }
            }
        }
    }

    override fun serialize(src: Boolean?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src ?: false)
    }
}
