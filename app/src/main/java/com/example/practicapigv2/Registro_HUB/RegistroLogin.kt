package com.example.practicapigv2.Registro_HUB

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.practicapigv2.Registro_HUB.BBDD.DataBaseUsuario
import com.example.practicapigv2.Registro_HUB.BBDD.Usuario
import com.example.practicapigv2.Registro_HUB.BBDD.UsuarioDao
import com.example.practicapigv2.Registro_HUB.RandomUserAPI.Picture
import com.example.practicapigv2.Registro_HUB.RandomUserAPI.RandomUserApiResponse
import com.example.practicapigv2.Registro_HUB.RandomUserAPI.RandomUserApiService
import com.example.practicapigv2.databinding.ActivityRegistroLoginBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        var urlElegida:String=""


        fotoPerfil(binding.avatar1,binding.url1)
        fotoPerfil(binding.avatar2,binding.url2)
       fotoPerfil(binding.avatar3,binding.url3)

        binding.datePickerID.setOnClickListener {
            datePicker()
        }

        binding.loginId.setOnClickListener {
            val intent = Intent(this@RegistroLogin, Login::class.java)
            startActivity(intent)
        }



        Log.d("HOLA","1:"+binding.url1.text.toString()+" 2:"+binding.url2.text+" 3:"+binding.url3.text)
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
                        fechaNacimiento = binding.datePickerID.text.toString(),
                        urlFoto=urlElegida
                    )
                    comprobacionUser(usuario,userDao)
        }
        binding.Aleatorio.setOnClickListener{
            fotoPerfil(binding.avatar1,binding.url1)
            fotoPerfil(binding.avatar2,binding.url2)
            fotoPerfil(binding.avatar3,binding.url3)
            Log.d("HOLA","1:"+binding.url1.text.toString()+" 2:"+binding.url2.text+" 3:"+binding.url3.text)
        }
        binding.aleatorioFemenino.setOnClickListener{
            fotoPerfilGenero(binding.avatar1,"female",binding.url1)
            fotoPerfilGenero(binding.avatar2,"female",binding.url2)
            fotoPerfilGenero(binding.avatar3,"female",binding.url3)
            Log.d("HOLA","1:"+binding.url1.text.toString()+" 2:"+binding.url2.text+" 3:"+binding.url3.text)

        }
        binding.aleatorioMasculino.setOnClickListener {
            fotoPerfilGenero(binding.avatar1,"male",binding.url1)
            fotoPerfilGenero(binding.avatar2,"male",binding.url2)
            fotoPerfilGenero(binding.avatar3,"male",binding.url3)
            Log.d("HOLA","1:"+binding.url1.text.toString()+" 2:"+binding.url2.text+" 3:"+binding.url3.text)
        }
        binding.avatar1.setOnClickListener{
            urlElegida=binding.url1.text.toString()
            Log.d("URL",urlElegida)
        }
        binding.avatar2.setOnClickListener{
            urlElegida=binding.url2.text.toString()
            Log.d("URL",urlElegida)
        }
        binding.avatar3.setOnClickListener{
            urlElegida=binding.url3.text.toString()
            Log.d("URL",urlElegida)
        }
    }

    private fun fotoPerfil(bind: ImageButton, url:TextView) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RandomUserApiService::class.java)
        val call = service.getRandomUser()

        call.enqueue(object : Callback<RandomUserApiResponse> {
            override fun onResponse(
                call: Call<RandomUserApiResponse>,
                response: Response<RandomUserApiResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("MARIO", "dENTRO DEL 1ER IF")
                    val imageUrl = response.body()?.results?.get(0)?.picture?.large
                    if (!imageUrl.isNullOrEmpty()) {
                        // Utiliza Picasso u otra biblioteca para cargar la imagen en el ImageView
                        Log.d("MARIO", "dENTRO DEL SEGUNDO IF")
                        Picasso.get().load(imageUrl).into(bind)
                        url.text = imageUrl

                    }
                }
            }

            override fun onFailure(call: Call<RandomUserApiResponse>, t: Throwable) {
                // Manejar el fallo de la solicitud
                Log.d("MARIO", "dERRRRRRRRRRR")
            }
        })


    }
    private fun fotoPerfilGenero(bind: ImageButton,genero:String,url:TextView) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RandomUserApiService::class.java)
        val call = service.getRandomUserGender(genero)

        call.enqueue(object : Callback<RandomUserApiResponse> {
            override fun onResponse(
                call: Call<RandomUserApiResponse>,
                response: Response<RandomUserApiResponse>
            ) {

                if (response.isSuccessful) {
                        Log.d("MARIO", "dENTRO DEL 1ER IF")
                    val imageUrl = response.body()?.results?.get(0)?.picture?.large
                        if (!imageUrl.isNullOrEmpty()) {
                            // Utiliza Picasso u otra biblioteca para cargar la imagen en el ImageView
                            Log.d("MARIO", "dENTRO DEL SEGUNDO IF")
                            Picasso.get().load(imageUrl).into(bind)
                            url.text = imageUrl
                        }

                }
            }

            override fun onFailure(call: Call<RandomUserApiResponse>, t: Throwable) {
                // Manejar el fallo de la solicitud
                Log.d("MARIO", "dERRRRRRRRRRR")
            }
        })

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
                        val intent = Intent(this@RegistroLogin, Login::class.java)
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
                    "Contraseña entre 4 y 10 caracteres, debe contener un numero"
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