// RetrofitInstance.kt
package com.example.loginconvocatoria.api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

//Instancia
    val api: ApiServiceNucleoDigital by lazy {
        Retrofit.Builder()
            .baseUrl("http://comedatos.qroo.gob.mx/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceNucleoDigital::class.java)
    }
}
