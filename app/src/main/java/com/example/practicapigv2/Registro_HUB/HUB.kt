package com.example.practicapigv2.Registro_HUB

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicapigv2.R
import com.example.practicapigv2.databinding.ActivityHubBinding
import com.example.practicapigv2.databinding.ActivityMainBinding
import com.example.practicapigv2.juegoDado.MainActivity
import com.example.practicapigv2.juegoDado.MainActivity2

class HUB : AppCompatActivity() {
    lateinit var binding: ActivityHubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fotoPerf:String= intent.getStringExtra("foto").toString()
        binding.juegoPigBoton.setOnClickListener {
            val intent = Intent(this@HUB, MainActivity::class.java)
            startActivity(intent)

        }
        println(fotoPerf)
    }
}