package com.example.practicapigv2.juegoDado

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practicapigv2.R
import com.example.practicapigv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var jugadores:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
        setContentView(binding.root)

        val items = listOf("Seleccione", "2", "3", "4")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        var seleccion: Boolean = false
        var seleccionJugador: Boolean = false
        var rondas: Int = 0

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.jugadoresID.adapter = adapter

        binding.rondasID.setOnClickListener {
            if (!binding.rondasID.text.toString().equals("")) {
                rondas = binding.rondasID.text.toString().toInt()
                if (rondas > 0) {
                    seleccionJugador = true

                } else {
                    seleccionJugador = false
                    Toast.makeText(
                        this,
                        "Por favor, ingrese un número válido de rondas.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.jugadoresID.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (seleccionJugador) {
                        if (!parent.selectedItem.equals(0)) {
                            jugadores = parent.getItemAtPosition(position).toString()
                            val intent = Intent(this@MainActivity, MainActivity2::class.java)

                            intent.putExtra("numJugadores", jugadores)
                            intent.putExtra("rondas", binding.rondasID.text.toString())
                            startActivity(intent)
                        }

                    } else {
                       parent.setSelection(0)
                        Toast.makeText(
                            this@MainActivity,
                            "Primero debe seleccionar el número de rondas.",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

    },3000)
        }

    }
