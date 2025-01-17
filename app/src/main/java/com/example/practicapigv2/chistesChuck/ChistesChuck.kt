package com.example.practicapigv2.chistesChuck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practicapigv2.databinding.ActivityChistesChuckBinding
import com.example.practicapigv2.juegoDado.MainActivity2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var binding: ActivityChistesChuckBinding

class ChistesChuck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityChistesChuckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val items = listOf("Seleccione","animal", "career", "celebrity", "dev", "explicit", "fashion", "food",
            "history", "money", "movie", "music", "political", "religion", "science",
            "sport" ,"travel")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorias.adapter = adapter

        binding.categorias.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (binding.categorias.selectedItem!="Seleccione"){
                binding.categoriaID.setText("Categoria: "+binding.categorias.selectedItem.toString())
                sacarChiste(binding.textView8, binding.categorias.selectedItem.toString())
               binding.categorias.setSelection(0)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



    }

    private fun sacarChiste(bind: TextView,categoria:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ChuckNorrisApiService::class.java)
        val call = service.getRandomValueCategory(categoria)
        Log.d("NORRIS",categoria)
        call.enqueue(object : Callback<ChuckNorrisApiResponse> {
            override fun onResponse(
                call: Call<ChuckNorrisApiResponse>,
                response: Response<ChuckNorrisApiResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("NORRIS", "DENTRO")
                    val chiste = response.body()?.value
                    println(chiste)
                    if (chiste != null) {
                        Log.d("NORRIS",chiste)
                    }else{
                        Log.d("NORRIS","NADA")
                    }

                     if (!chiste.isNullOrEmpty()) {

                        bind.setText(chiste)
                    }
                }
            }

            override fun onFailure(call: Call<ChuckNorrisApiResponse>, t: Throwable) {
                // Manejar el fallo de la solicitud
                Log.d("MARIO", "dERRRRRRRRRRR")
            }
        })


    }
}