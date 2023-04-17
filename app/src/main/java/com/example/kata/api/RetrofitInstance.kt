package com.example.kata.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit = Retrofit.Builder()
        .baseUrl(WeatherService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}