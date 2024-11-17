package com.example.practicapigv2



import java.io.Serializable
import kotlin.random.Random



class Jugador(val nombre: String): Serializable {

    var puntuacion:Int=0

    var puntuacionTurno:Int=0

     fun tirar(): Int {
        var dado= Random.nextInt(1,7)
        when(dado) {
            1 -> puntuacionTurno=puntuacionTurno+1
            2 -> puntuacionTurno=puntuacionTurno+2
            3 -> puntuacionTurno=puntuacionTurno+3
            4 -> puntuacionTurno=puntuacionTurno+4
            5 -> puntuacionTurno=puntuacionTurno+5
            6 -> puntuacionTurno=puntuacionTurno+6
        }
        return dado

    }

    override fun toString(): String {
        return "Jugador(puntuacion=$puntuacion, nombre='$nombre')"
    }


}