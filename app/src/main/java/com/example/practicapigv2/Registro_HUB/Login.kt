package com.example.practicapigv2.Registro_HUB

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.practicapigv2.R
import com.example.practicapigv2.Registro_HUB.BBDD.DataBaseUsuario
import com.example.practicapigv2.Registro_HUB.BBDD.UserPreferences
import com.example.practicapigv2.databinding.ActivityLoginBinding
import com.example.practicapigv2.databinding.ActivityRegistroLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var userPreferences: UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent: Intent

        userPreferences = UserPreferences(applicationContext)
        observeData()
        quitarCheckConCambios()

        val userDao = DataBaseUsuario.getDatabase(this@Login).usuarioDao()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                userDao.obtenerTodosUsuarios()
            }

            binding.registerID.setOnClickListener({
                intent = Intent(this@Login, RegistroLogin::class.java)
                startActivity(intent)
            })
            binding.loginId.setOnClickListener {

                lifecycleScope.launch {

                    withContext(Dispatchers.IO) {

                        val usuario = userDao.obtenerUsuario(
                            binding.nombreID.text.toString(),
                            binding.contrasenyaID.text.toString()
                        )

                        if (usuario == null) {
                            binding.incorrectoID.text = "Nombre o Contraseña incorrectos"
                        } else {
                            intent = Intent(this@Login, HUB::class.java)
                            intent.putExtra("foto", usuario.urlFoto)
                            startActivity(intent)
                        }
                    }
                }
            }
        }


    }


    private fun quitarCheckConCambios() {
        binding.nombreID.setOnClickListener {
            binding.recordarID.isChecked = false
        }

        binding.contrasenyaID.setOnClickListener {
            binding.recordarID.isChecked = false
        }
        binding.nombreID.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.recordarID.isChecked = false
            }
        }
        binding.contrasenyaID.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.recordarID.isChecked = false
            }
        }
    }

    private fun saveUserData(name: String, contra: String,recordar:Boolean) {

        if (recordar) {
            lifecycleScope.launch {
                userPreferences.saveUserData(name, contra, recordar)

            }
        }
    }
    override fun onStop() {
        super.onStop()


        val recordar = binding.recordarID.isChecked

        if (recordar) {

            val name = binding.nombreID.text.toString()
            val contra = binding.contrasenyaID.text.toString()
            saveUserData(name, contra, recordar)
        } else {
            lifecycleScope.launch {
                userPreferences.saveUserData("", "", false)
            }
        }
    }

    private fun observeData() {

        lifecycleScope.launch {
            userPreferences.userRemember.collect { remember ->
                binding.recordarID.isChecked = remember
                if (remember) {
                    launch { userPreferences.userName.collect { name ->
                        binding.nombreID.setText(name)
                    } }
                    launch { userPreferences.userContrasenya.collect { contra ->
                        binding.contrasenyaID.setText(contra)}

                    }
                }
                if(!remember) {
                    binding.nombreID.setText("")
                    binding.contrasenyaID.setText("")
                }
            }
        }
    }
}
