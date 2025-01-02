package com.example.loginconvocatoria.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitClient {
    //considerar la ip actual de la pc servidor
    private const val BASE_URL = "http://192.168.1.72:8000/api/"
    //192.168.1.72

    val instance: ApiServiceLogin by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceLogin::class.java)
    }
}
