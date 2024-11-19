package com.example.practicapigv2.Registro_HUB.BBDD

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UsuarioDao {

    @Insert
     fun insertarUsuario(usuario: Usuario)
}