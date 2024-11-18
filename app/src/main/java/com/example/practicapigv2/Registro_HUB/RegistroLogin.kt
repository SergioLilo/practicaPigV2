package com.example.practicapigv2.Registro_HUB

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicapigv2.R
import com.example.practicapigv2.databinding.ActivityHubBinding
import com.example.practicapigv2.databinding.ActivityRegistroLoginBinding
import com.example.practicapigv2.juegoDado.MainActivity2


class RegistroLogin : AppCompatActivity() {
    lateinit var binding: ActivityRegistroLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.datePickerID.isFocusable=false
        binding.datePickerID.setOnClickListener({
            datePicker()

        })
        binding.loginId.setOnClickListener({

            val intent = Intent(this@RegistroLogin, Login::class.java)
                    startActivity(intent)

        })

    }

    private fun datePicker(){
        // Valores por defecto del DatePicker
        val year = 2000
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year1)
                binding.datePickerID.setText(dateChoice)
                //temp = dateChoice
            }, year, month, day
        )
        datePickerDialog.show()
    }
}