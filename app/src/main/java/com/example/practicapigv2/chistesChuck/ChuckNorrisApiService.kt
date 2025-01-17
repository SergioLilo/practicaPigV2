package com.example.practicapigv2.chistesChuck


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  ChuckNorrisApiService{

    @GET("jokes/")
    fun getRandomUserGender(@Query("category") gender: String): Call<ChuckNorrisApiResponse>
}



