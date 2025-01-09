package com.example.practicapigv2.Registro_HUB.BBDD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
     fun insertarUsuario(usuario: Usuario)
     @Query("Select * from usuario where usuario_nombre= :nombreIntroducido")
     fun comprobarPorNombre(nombreIntroducido:String):Usuario?
    @Query("Select * from usuario")
    fun obtenerTodosUsuarios(): List<Usuario>
    @Query("Select * from usuario where usuario_nombre= :nombre and usuario_contrasenya= :contrasenya")
    fun obtenerUsuario(nombre:String,contrasenya:String): Usuario
}