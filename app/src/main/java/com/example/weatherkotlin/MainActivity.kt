package com.example.weatherkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.net.URL


class MainActivity : AppCompatActivity() {

    private var editTextCity: EditText? = null
    private var buttonSearch: Button? = null
    private var textViewWeather: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCity = findViewById(R.id.editTextCity)
        buttonSearch = findViewById(R.id.buttonSearch)
        textViewWeather = findViewById(R.id.textViewWeather)

        buttonSearch?.setOnClickListener{
            if (editTextCity?.text?.toString()?.trim()!!.equals("")!!) {
                Toast.makeText(this, "Введите название города", Toast.LENGTH_SHORT).show()
            } else {
                val key:String = "d413d0e786af04c647891498baac4b66"
                var city:String = editTextCity?.text?.toString()!!.trim()
                var url:String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=ru"

                requestWeatherData(url)

            }
        }

    }

    private fun requestWeatherData (url:String){
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url,
            {
                    result -> Log.d("MyLog", result.toString())
            },
            {
                    error -> Log.d("MyLog", error.toString())
            }
        )
        queue.add(request)
    }
}