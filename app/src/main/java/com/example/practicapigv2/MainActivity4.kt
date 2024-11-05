package com.example.practicapigv2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicapigv2.databinding.ActivityMain3Binding
import com.example.practicapigv2.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val textoFinal = intent.getStringExtra("final")
        binding.textoFinalId.setText(textoFinal)

        }
    }
