package com.example.practicapigv2

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_recyclerview.CustomAdapter
import com.example.practicapigv2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val numJugadores = intent.getStringExtra("numJugadores")?.toInt()?:0
        val nombres = listOf(
            "Aitor Tilla","Ana Conda", "Armando Broncas", "Aurora Boreal",
            "Bartolo Mesa","Carmen Mente", "Dolores Delirio", "Elsa Pato",
            "Enrique Cido","Esteban Dido", "Elba Lazo", "Fermin Tado",
            "Lola Mento", "Luz Cuesta", "Margarita Flores", "Paco Tilla",
            "Pere Gil", "Pío Nono", "Salvador Tumbado", "Zoila Vaca"
        )

        var listaRecicle= ArrayList<RecyclerView>()

        listaRecicle.add(binding.rec1)
        listaRecicle.add(binding.rec2)
        listaRecicle.add(binding.rec3)
        listaRecicle.add(binding.rec4)

        /*
        for (recyclerView in listaRecicle) {
            recyclerView.visibility = View.GONE
        }

        listaRecicle.get(0).visibility=View.VISIBLE
        val recyclerView: RecyclerView = listaRecicle[0]
        recyclerView.layoutManager = LinearLayoutManager(this)
        val customAdapter = CustomAdapter(nombres)
        recyclerView.adapter = customAdapter*/

        /*
        for (i in 0 until numJugadores)  {
            if (i < listaRecicle.size) { // Asegurarse de no exceder el tamaño de la lista
                listaRecicle[i].visibility = View.VISIBLE
                val recyclerView: RecyclerView = listaRecicle[i]
                recyclerView.layoutManager = LinearLayoutManager(this)

                // Puedes pasar datos específicos a cada adaptador si es necesario
                // Aquí se asigna el mismo conjunto de nombres para simplificar
                val customAdapter = CustomAdapter(nombres) // Puedes modificar esto si deseas diferentes datos por RecyclerView
                recyclerView.adapter = customAdapter

                }
            }*/
        }
    }
