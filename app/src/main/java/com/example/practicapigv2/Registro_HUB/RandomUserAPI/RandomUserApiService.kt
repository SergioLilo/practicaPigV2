package com.example.practicapigv2.Registro_HUB.RandomUserAPI

import retrofit2.Call
import retrofit2.http.GET

interface  RandomUserApiService{
    @GET("api/")
    fun getRandomUser(): Call<RandomUserApiResponse>
}



