package com.example.practicapigv2.Registro_HUB.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(

    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "usuario_nombre")var nombre:String,
    @ColumnInfo(name = "usuario_contrasenya") var contrasenya:String,
    @ColumnInfo(name = "usuario_fechaNacimiento")var fechaNacimiento:String,
    @ColumnInfo(name = "usuario_fotoPerfil")var urlFoto:String,

)
