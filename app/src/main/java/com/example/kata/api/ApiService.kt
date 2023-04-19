package com.example.kata.api

import com.example.kata.Weather
import retrofit2.Response
import retrofit2.http.*



interface ApiService {

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("q") name: String, @Query("appid") appId: String
    ): Response<Weather>

}