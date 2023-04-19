package com.example.kata.api

import com.example.kata.Weather
import retrofit2.Response
import retrofit2.http.*

private const val API_KEY = "059d75c39f3d4fd6ce01fc6215fa515c"

interface ApiService {

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("q") name: String = "London", @Query("appid") appId: String = API_KEY
    ): Response<Weather>

}