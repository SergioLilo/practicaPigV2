package com.example.practicapigv2.Registro_HUB.BBDD

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("user_prefs")
class UserPreferences(private val context: Context) {

    companion object {
        val NOMBE_KEY = stringPreferencesKey("user_nombre")
        val CONTRASENYA_KEY = stringPreferencesKey("user_contrasenya")
        val USER_REMEMBER = booleanPreferencesKey("user_recordar")

    }

    // Guardar datos
    suspend fun saveUserData(name: String, contrasenya: String,recordar: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOMBE_KEY] = name
            preferences[CONTRASENYA_KEY] = contrasenya
            preferences[USER_REMEMBER] = recordar


        }
    }

    // Obtener datos
    val userName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NOMBE_KEY] ?: ""
        }

    val userContrasenya: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[CONTRASENYA_KEY] ?: ""
        }
    val userRemember: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[USER_REMEMBER] ?: false
        }

}