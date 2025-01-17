package com.example.practicapigv2.chistesChuck

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practicapigv2.R
import com.example.practicapigv2.Registro_HUB.RandomUserAPI.RandomUserApiResponse
import com.example.practicapigv2.Registro_HUB.RandomUserAPI.RandomUserApiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChistesChuck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes_chuck)


    }
    /*
    private fun fotoPerfil(bind: ImageButton, url: TextView) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RandomUserApiService::class.java)
        val call = service.getRandomUser()

        call.enqueue(object : Callback<ChuckNorrisApiResponse> {
            override fun onResponse(
                call: Call<ChuckNorrisApiResponse>,
                response: Response<ChuckNorrisApiResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("MARIO", "dENTRO DEL 1ER IF")
                    val imageUrl = response.body()?.results?.get(0)?.picture?.large
                    if (!imageUrl.isNullOrEmpty()) {
                        // Utiliza Picasso u otra biblioteca para cargar la imagen en el ImageView
                        Log.d("MARIO", "dENTRO DEL SEGUNDO IF")
                        Picasso.get().load(imageUrl).into(bind)
                        url.text = imageUrl

                    }
                }
            }

            override fun onFailure(call: Call<RandomUserApiResponse>, t: Throwable) {
                // Manejar el fallo de la solicitud
                Log.d("MARIO", "dERRRRRRRRRRR")
            }
        })


    }*/
}