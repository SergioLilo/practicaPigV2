package com.example.practicapigv2.Registro_HUB.RandomUserAPI

data class RandomUserApiResponse(
    val results: List<User>
)

data class User(
    var gender: String,
    val name: Name,
    val picture: Picture
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

