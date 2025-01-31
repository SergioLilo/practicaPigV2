package com.example.practicapigv2.fotoVideo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicapigv2.R
import com.example.practicapigv2.databinding.ActivityFotoVideoBinding
import com.example.practicapigv2.databinding.ActivityListadoFotoVideoBinding

class ListadoFotoVideo : AppCompatActivity() {
    private lateinit var viewBinding: ActivityListadoFotoVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_foto_video)
        viewBinding = ActivityListadoFotoVideoBinding.inflate(layoutInflater)


    }
}