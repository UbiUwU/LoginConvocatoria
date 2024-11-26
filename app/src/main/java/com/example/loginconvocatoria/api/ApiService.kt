package com.example.loginconvocatoria.api

import com.example.loginconvocatoria.models.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("NucleoDigital")
    suspend fun getNucleoDigital(
        @Header("Authorization") token: String,
        @Body credentials: Map<String, String>
    ): Response<ApiResponse>
}
