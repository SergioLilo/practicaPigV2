package com.example.practicapigv2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicapigv2.databinding.ActivityMain2Binding
import com.example.practicapigv2.databinding.ActivityMain3Binding
import java.util.Collections
import kotlin.random.Random

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val jugadores = ArrayList<Jugador>()
        val rondas= intent.getStringExtra("rondas")?.toInt()
        println(rondas)
        val nombresElegidos = intent.getStringArrayListExtra("nombresElegidos")
        println("Nombres de Activity3: "+nombresElegidos)


        for (i in 0.. nombresElegidos!!.size-1){

            jugadores.add(Jugador(nombresElegidos.get(i)))

        }
        Collections.shuffle(jugadores)
        println(jugadores+" "+rondas)


    }
    class Jugador(s: String) {

        var puntuacion:Int=0
        var nombre:String=s

        public fun tirar(): Int {
            var dado= Random.nextInt(1,7)
            when(dado) {
                1 -> puntuacion=0
                2 -> puntuacion=puntuacion+2
                3 -> puntuacion=puntuacion+3
                4 -> puntuacion=puntuacion+4
                5 -> puntuacion=puntuacion+5
                6 -> puntuacion=puntuacion+6
            }
            return dado

        }

        override fun toString(): String {
            return "Jugador(puntuacion=$puntuacion, nombre='$nombre')"
        }


    }
}