package com.example.practicapigv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicapigv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var jugadores:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Seleccione:","2", "3", "4")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        var seleccion:Boolean=false


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asigna el adaptador al Spinner
        binding.jugadoresID.adapter = adapter


            binding.jugadoresID.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {

                        if (!seleccion){
                            seleccion=true
                        }else {

                            jugadores = parent.getItemAtPosition(position).toString()
                            val intent = Intent(this@MainActivity, MainActivity2::class.java)
                            intent.putExtra("numJugadores", jugadores)
                            startActivity(intent)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }


        }
    }
