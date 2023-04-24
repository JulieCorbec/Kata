package com.example.kata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kata.api.ApiClient
import com.example.kata.recyclerview.WeatherAdapter
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    lateinit var btnAgainLayout: LinearLayout
    lateinit var loadingLayout: LinearLayout
    lateinit var loadingText: TextView
    lateinit var progressBar: ProgressBar
    lateinit var customAdapter: WeatherAdapter
    lateinit var btnAgain: Button

    var indexText = 0
    var indexCity = 0

    val API_KEY = "059d75c39f3d4fd6ce01fc6215fa515c"

    var citiesList: List<String> = listOf("Rennes", "Paris", "Nantes", "Bordeaux", "Lyon", "Versailles", "Rouen", "Marseille", "Londres", "Toulouse", "Berlin")

    var weatherList = mutableListOf<Weather>()

    private val textList: List<String> = listOf(
        "Nous téléchargeons les données...",
        "C'est presque fini...",
        "Plus que quelques secondes avant d'avoir le résultat..."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val recyclerView: RecyclerView = findViewById(R.id.rv_weather)
        customAdapter = WeatherAdapter(this, weatherList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        loadingText = findViewById(R.id.loading_text)
        progressBar = findViewById(R.id.progress_bar)
        btnAgainLayout = findViewById(R.id.btn_again_layout)
        loadingLayout = findViewById(R.id.loading_layout)
        btnAgain = findViewById(R.id.btn_again)

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


        object : CountDownTimer(10000, 1000) {

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
                //  loadingText.text = "fini !"
                loadingText.visibility = View.GONE
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                btnAgainLayout.visibility = View.VISIBLE


            }
        }.start()

        object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                // Appel de la fonction pour faire un appel à l'API
                weatherCallByCity()
            }

            override fun onFinish() {
                loadingText.text = "fini !"
                customAdapter.notifyDataSetChanged()
            }
        }.start()

        btnAgain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            weatherList.clear()
            Log.d("WEATHER", weatherList.toString())
        }
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
                    weatherList.add(content!!)
                    Log.d("WEATHER", content!!.toString())
                } else {
                    Toast.makeText(
                        this@WeatherActivity,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()

                }

                Log.d("WEATHER", weatherList.toString())

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

