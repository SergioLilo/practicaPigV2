package com.example.practicapigv2.Registro_HUB.RandomUserAPI


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  RandomUserApiService{
    @GET("api/")
    fun getRandomUser(): Call<RandomUserApiResponse>
    @GET("api/")
    fun getRandomUserGender(@Query("gender") gender: String): Call<RandomUserApiResponse>
}



