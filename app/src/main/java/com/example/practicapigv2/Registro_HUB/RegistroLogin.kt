package com.example.practicapigv2.Registro_HUB

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.practicapigv2.R
import com.example.practicapigv2.Registro_HUB.BBDD.DataBaseUsuario
import com.example.practicapigv2.Registro_HUB.BBDD.Usuario
import com.example.practicapigv2.databinding.ActivityHubBinding
import com.example.practicapigv2.databinding.ActivityRegistroLoginBinding
import com.example.practicapigv2.juegoDado.MainActivity2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegistroLogin : AppCompatActivity() {
    lateinit var binding: ActivityRegistroLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datePickerID.isFocusable = false

        binding.datePickerID.setOnClickListener {
            datePicker()
        }

        binding.loginId.setOnClickListener {
            val intent = Intent(this@RegistroLogin, Login::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
        val userDao = DataBaseUsuario.getDatabase(this@RegistroLogin).usuarioDao()

        binding.registerID.setOnClickListener {

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    val usuario: Usuario = Usuario(
                        nombre = binding.nombreID.text.toString(),
                        contrasenya = binding.contrasenyaID.text.toString(),
                        fechaNacimiento = binding.datePickerID.text.toString()
                    )
                    userDao.insertarUsuario(usuario)

                }
            }

        }

    }
}


    }

    private fun datePicker(){

        val year = 2000
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year1)
                binding.datePickerID.setText(dateChoice)

            }, year, month, day
        )
        datePickerDialog.show()
    }
}