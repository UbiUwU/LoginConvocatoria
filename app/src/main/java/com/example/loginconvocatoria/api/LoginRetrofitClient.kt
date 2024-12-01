package com.example.loginconvocatoria.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitClient {
    private const val BASE_URL = "http://192.168.0.6:8000/api/"


    val instance: ApiServiceLogin by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceLogin::class.java)
    }
}
