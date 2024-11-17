package com.example.practicapigv2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practicapigv2.databinding.ActivityMain3Binding
import com.example.practicapigv2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val jugadores = ArrayList<Jugador>()
        val rondas= intent.getStringExtra("rondas")?.toInt()?:0
        println(rondas)
        val nombresElegidos = intent.getStringArrayListExtra("nombresElegidos")
        println("Nombres de Activity3: "+nombresElegidos)


        for (i in 0.. nombresElegidos!!.size-1){

            jugadores.add(Jugador(nombresElegidos[i]))
        }
        jugadores.shuffle()

        println(jugadores+" "+rondas)
        var num: Int=jugadores.size
        var dado: Int = 0
        var numtirado = 0
        var turno = 1
        var numTurno=0
        var mensajeFinal:String =""
        var clasificacionFinal:String=""
        val intent = Intent(this@MainActivity3, MainActivity4::class.java)

        binding.TurnJug.text = "Turno de " + jugadores[numTurno].nombre
        binding.rondaID.visibility = View.VISIBLE
        binding.clasificacionID.visibility = View.VISIBLE
        binding.rondaID.text = "RONDA: " + turno
        clasificacion(binding,jugadores)
        binding.pasarID.visibility = View.GONE

        binding.botonTirar.setOnClickListener {

            quitarDado(binding)
            dado = jugadores[numTurno].tirar()
            Handler().postDelayed({
                binding.pasarID.visibility = View.GONE
                binding.botonTirar.visibility = View.GONE
                animacion(binding)

            }, 50)

            Handler().postDelayed({
                if (numtirado != 0) binding.pasarID.visibility = View.VISIBLE
                binding.botonTirar.visibility = View.VISIBLE
                binding.puntJUG.text=jugadores[numTurno].nombre+" HA SACADO "+dado
                ponerDado(binding, dado)

                jugadores[numTurno].puntuacion=jugadores[numTurno].puntuacion+dado
                clasificacion(binding, jugadores)
            }, 1500)
            numtirado++

            Handler().postDelayed({


                if (dado == 1) {
                    jugadores[numTurno].puntuacion -=  jugadores[numTurno].puntuacionTurno
                    jugadores[numTurno].puntuacionTurno=0
                    clasificacion(binding, jugadores)
                    numTurno++
                    if (numTurno == num) {
                        numTurno = 0
                        turno++
                        binding.rondaID.text = "RONDA: " + turno
                    }

                    binding.TurnJug.text = "Turno de " +jugadores[numTurno].nombre
                    binding.pasarID.visibility = View.GONE
                    numtirado = 0
                }

                binding.pasarID.setOnClickListener {
                    jugadores[numTurno].puntuacionTurno=0
                    numTurno++

                    if (numTurno == num) {
                        numTurno = 0
                        turno++
                        binding.rondaID.text = "RONDA: " + turno
                    }
                    binding.pasarID.visibility = View.GONE
                    binding.TurnJug.text = "Turno de " + jugadores[numTurno].nombre
                    numtirado = 0


                    if (turno == rondas + 1) {
                        mensajeFinal=obtenerGanadorOEmpate(jugadores)
                        clasificacionFinal=clasificacion(binding, jugadores)
                        intent.putExtra("final",mensajeFinal)
                        intent.putExtra("clasificacion",clasificacionFinal)
                        startActivity(intent)
                    }
                }

                if (turno == rondas + 1) {
                    mensajeFinal=obtenerGanadorOEmpate(jugadores)
                    clasificacionFinal=clasificacion(binding, jugadores)
                    intent.putExtra("final",mensajeFinal)
                    intent.putExtra("clasificacion",clasificacionFinal)
                    startActivity(intent)
                }

            },1555)
        }

    }

    private fun ponerDado(binding: ActivityMain3Binding,dado:Int) {
        when (dado) {
            1 -> binding.dado1.visibility = View.VISIBLE
            2 -> binding.dado2.visibility = View.VISIBLE
            3 -> binding.dado3.visibility = View.VISIBLE
            4 -> binding.dado4.visibility = View.VISIBLE
            5 -> binding.dado5.visibility = View.VISIBLE
            6 -> binding.dado6.visibility = View.VISIBLE
        }
    }
    private fun quitarDado(binding: ActivityMain3Binding){

        binding.dado1.visibility = View.GONE
        binding.dado2.visibility = View.GONE
        binding.dado3.visibility = View.GONE
        binding.dado4.visibility = View.GONE
        binding.dado5.visibility = View.GONE
        binding.dado6.visibility = View.GONE

    }
    private fun animacion(binding: ActivityMain3Binding){

        //binding.pasarID.visibility=View.GONE
        val carasDelDado = arrayOf(
            R.drawable.dado1,
            R.drawable.dado2,
            R.drawable.dado3,
            R.drawable.dado4,
            R.drawable.dado5,
            R.drawable.dado6
        )

        for (i in 1..6){

            val caraAleatoria = carasDelDado[Random.nextInt(6)]
            Handler().postDelayed({

                val caraAleatoria = carasDelDado[Random.nextInt(6)]

                binding.dadoRand.setImageResource(caraAleatoria)

                binding.dadoRand.visibility = View.VISIBLE

                Handler().postDelayed({
                    binding.dadoRand.visibility = View.GONE
                }, 150L)

            }, (i * 200L))
        }

    }

    private fun clasificacion(binding: ActivityMain3Binding, jugadores: ArrayList<Jugador>): String {
        var mensaje:String=""
        for (i in 0 .. jugadores.size-1){

            mensaje=mensaje.plus("\n"+jugadores[i].nombre+": "+jugadores[i].puntuacion)

        }
        binding.clasificacionID.text=mensaje
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