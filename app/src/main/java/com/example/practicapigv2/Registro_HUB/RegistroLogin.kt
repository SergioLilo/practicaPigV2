package com.example.practicapigv2.Registro_HUB

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.practicapigv2.R
import com.example.practicapigv2.Registro_HUB.BBDD.DataBaseUsuario
import com.example.practicapigv2.Registro_HUB.BBDD.Usuario
import com.example.practicapigv2.Registro_HUB.BBDD.UsuarioDao
import com.example.practicapigv2.databinding.ActivityHubBinding
import com.example.practicapigv2.databinding.ActivityRegistroLoginBinding
import com.example.practicapigv2.juegoDado.MainActivity
import com.example.practicapigv2.juegoDado.MainActivity2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


class RegistroLogin : AppCompatActivity() {
    lateinit var binding: ActivityRegistroLoginBinding
    @RequiresApi(Build.VERSION_CODES.O)
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




        val userDao = DataBaseUsuario.getDatabase(this@RegistroLogin).usuarioDao()
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                userDao.obtenerTodosUsuarios()
            }

        }
        binding.registerID.setOnClickListener {
                    var nombreLength:Boolean=false
                    var contrasenyaLength:Boolean=false
                    var edadCorrecta:Boolean=false

                    val usuario: Usuario = Usuario(
                        nombre = binding.nombreID.text.toString(),
                        contrasenya = binding.contrasenyaID.text.toString(),
                        fechaNacimiento = binding.datePickerID.text.toString()
                    )
                    comprobacionUser(usuario,userDao)
        }
    }
    private fun resetMensajes(){
        binding.nombreIncorrectoID.text=""
        binding.contrasenyaIncorrectoID.text=""
        binding.fechaIncorrectaID.text=""
        binding.userExistenteID.text=""
        binding.terminosID.text=""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private  fun comprobacionUser(
        usuario: Usuario,
        userDao:UsuarioDao
    ) {
        resetMensajes()
        lifecycleScope.launch {
            var nombreLength1 = false
            var contrasenyaLength1 = false
            var edadCorrecta1 = false
            var termCond=false

            var usuarioExistente:Boolean=false
            withContext(Dispatchers.IO) {

                if (binding.terms.isChecked){
                    termCond=true
                }
                if (usuario.nombre!=null && usuario.nombre.length in 4..10) {
                    nombreLength1 = true
                }
                if ((usuario.contrasenya!=null && usuario.contrasenya.length in 4..10)) {

                    for (i in 0..9) {
                        if (usuario.contrasenya.contains(i.toString())) {
                            contrasenyaLength1 = true
                        }
                    }
                }
                edadCorrecta1 = esMenorDe16Anios(usuario.fechaNacimiento)
                if (nombreLength1 && contrasenyaLength1 && edadCorrecta1 && termCond) {
                    val comprobacionNombre = userDao.comprobarPorNombre(usuario.nombre)
                    if (comprobacionNombre == null) {
                        userDao.insertarUsuario(usuario)
                        val intent = Intent(this@RegistroLogin, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        usuarioExistente=true
                    }
                }

            }

            withContext(Dispatchers.Main) {
                if (usuarioExistente) {
                    binding.userExistenteID.text = "Usuario ya existente, cambie el nombre"
                }

             if (!nombreLength1) {

                binding.nombreIncorrectoID.text = "El nombre debe tener entre 4 y 10 caracteres"
        }
                if (!contrasenyaLength1) {

                binding.contrasenyaIncorrectoID.text =
                    "Contraseña entre 4 y 10 caracteres y debe contener un numero"
        }
                if (!edadCorrecta1) {

                binding.fechaIncorrectaID.text = "Debes tener mas de 16 años"
                }
                if (!termCond){
                    binding.terminosID.setText("Acepte los Terminos y Condiciones")
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
    @RequiresApi(Build.VERSION_CODES.O)

    fun esMenorDe16Anios(fechaNacimientoString: String): Boolean {

        if (fechaNacimientoString!="") {
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val comprobacion = fechaNacimientoString.split("-")
            val dia = comprobacion[0]
            val mes = comprobacion[1]


            if (dia.length == 1) formatter = DateTimeFormatter.ofPattern("d-MM-yyyy")
            if (mes.length == 1) formatter = DateTimeFormatter.ofPattern("dd-M-yyyy")
            if (mes.length == 1 && dia.length == 1) formatter =
                DateTimeFormatter.ofPattern("d-M-yyyy")


            val fechaNacimiento = LocalDate.parse(fechaNacimientoString, formatter)
            val fechaActual = LocalDate.now()

            val edad = Period.between(fechaNacimiento, fechaActual).years
            return edad>16
        }
        return false
    }
}