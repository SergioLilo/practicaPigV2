package com.example.practicapigv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a04_recyclerview.CustomAdapter
import com.example.practicapigv2.databinding.ActivityMain2Binding

val nombres = listOf(
    "Aitor Tilla","Ana Conda", "Armando Broncas", "Aurora Boreal",
    "Bartolo Mesa","Carmen Mente", "Dolores Delirio", "Elsa Pato",
    "Enrique Cido","Esteban Dido", "Elba Lazo", "Fermin Tado",
    "Lola Mento", "Luz Cuesta", "Margarita Flores", "Paco Tilla",
    "Pere Gil", "Pío Nono", "Salvador Tumbado", "Zoila Vaca"
)
private val nombresEleccion = mutableListOf<String>()
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var numeroElecciones:Int=0

        val numJugadores = intent.getStringExtra("numJugadores")?.toInt()?:0
        val rondas = intent.getStringExtra("rondas")?.toInt()?:0

        val recyclerViews = listOf(
            binding.rec1,
            binding.rec2,
            binding.rec3,
            binding.rec4
        )
        val textoJugadores = listOf(
           binding.texto1,
           binding.texto2,
           binding.texto3,
           binding.texto4

        )
        for (i in 0 until textoJugadores.size) {
            textoJugadores[i].apply { visibility = View.GONE }
        }
        for (i in 0 until numJugadores) {
            textoJugadores[i].apply { visibility = View.VISIBLE }
        }

        for (i in 0 until numJugadores) {
            recyclerViews[i].apply {
                visibility = View.VISIBLE
                layoutManager = LinearLayoutManager(this@MainActivity2)
                adapter = CustomAdapter(nombres,  nombresEleccion){
                    visibility=View.GONE
                    println(nombresEleccion)
                    numeroElecciones++

                    if (numeroElecciones==numJugadores){
                        val intent = Intent(this@MainActivity2, MainActivity3::class.java)
                        intent.putStringArrayListExtra("nombresElegidos", ArrayList(nombresEleccion))

                        intent.putExtra("rondas",rondas.toString())
                        startActivity(intent)
                    }
                }

            }
        }




    }

}
