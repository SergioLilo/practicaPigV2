package com.example.practicapigv2.juegoDado

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.practicapigv2.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugadores = intent.getSerializableExtra("listaJugadores") as? ArrayList<Jugador> ?: ArrayList()

        val clasificacion:String=clasificacion(jugadores)
        val textoFinal:String=obtenerGanadorOEmpate(jugadores)

        binding.textoFinalId.setText(textoFinal)
        binding.clasificacionFinalID.setText(clasificacion)


        }

    private fun clasificacion( jugadores: ArrayList<Jugador>): String {
        var mensaje:String=""
        for (i in 0 .. jugadores.size-1){

            mensaje=mensaje.plus("\n"+jugadores[i].nombre+": "+jugadores[i].puntuacion)

        }

        return mensaje
    }
    fun obtenerGanadorOEmpate(jugadores: ArrayList<Jugador>): String {


        val maxPuntuacion = jugadores.maxOf { it.puntuacion }


        val ganadores = jugadores.filter { it.puntuacion == maxPuntuacion }


        return if (ganadores.size == 1) {
            "El ganador es ${ganadores[0].nombre} con una puntuación de $maxPuntuacion."
        } else {
            val nombresEmpatados = ganadores.joinToString(", ") { it.nombre }
            "Han empatado: $nombresEmpatados con una puntuación de $maxPuntuacion."
        }
    }

    }
