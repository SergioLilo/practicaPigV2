package com.example.practicapigv2.chistesChuck


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  ChuckNorrisApiService{

    @GET("jokes/random")
    fun getRandomValue(): Call<ChuckNorrisApiResponse>

    @GET("jokes/random")
    fun getRandomValueCategory(@Query("category") category:String): Call<ChuckNorrisApiResponse>
}



