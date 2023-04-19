package com.example.kata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kata.api.ApiClient
import com.example.kata.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    lateinit var loadingText: TextView
    lateinit var progressBar: ProgressBar

    val API_KEY = "059d75c39f3d4fd6ce01fc6215fa515c"

    var citiesList: List<String> = listOf("Rennes", "Paris", "Nantes", "Bordeaux", "Lyon")

    var indexText = 0
    var indexCity = 0

    private val textList: List<String> = listOf(
        "Nous téléchargeons les données...",
        "C'est presque fini...",
        "Plus que quelques secondes avant d'avoir le résultat..."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        loadingText = findViewById(R.id.loading_text)
        progressBar = findViewById(R.id.progress_bar)
        var progressBarStatus = 0
        var plus = 0

        Thread(Runnable {

            while (progressBarStatus < 100) {
                plus += 1
                Thread.sleep(600)

                progressBarStatus = plus

                progressBar.progress = progressBarStatus
            }

        }).start()



        object : CountDownTimer(60000, 6000) {

            override fun onTick(millisUntilFinished: Long) {

                // Appel de la fonction pour modifier le texte en boucle
                if (indexText <= (textList.size) - 1) {
                    updateText()
                } else {
                    indexText = 0
                    updateText()
                }

            }

            override fun onFinish() {
                loadingText.text = "fini !"
            }
        }.start()

        object : CountDownTimer(60000, 10000) {

            override fun onTick(millisUntilFinished: Long) {

                // Appel de la fonction pour faire un appel à l'API
                weatherCallByCity()
            }

            override fun onFinish() {
                loadingText.text = "fini !"
            }
        }.start()

    }

    fun updateText() {
        // Si mon index ne dépasse pas la taille de ma liste - 1, je l'incrémente
        loadingText.text = textList[indexText]
        indexText++
    }

    fun weatherCallByCity() {
        if (indexCity <= (citiesList.size) - 1) {
            executeCall()
            indexCity++
        }
    }

    private fun executeCall() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.apiService.getWeather(citiesList[indexCity], API_KEY)

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    Log.d("WEATHER", content.toString())
                } else {
                    Toast.makeText(
                        this@WeatherActivity,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@WeatherActivity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

