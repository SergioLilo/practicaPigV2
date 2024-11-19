package com.example.practicapigv2.Registro_HUB.BBDD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract  class DataBaseUsuario: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao

    companion object{
        @Volatile
        private var INSTANCE: DataBaseUsuario? = null

        fun getDatabase(context: Context): DataBaseUsuario {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseUsuario::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}