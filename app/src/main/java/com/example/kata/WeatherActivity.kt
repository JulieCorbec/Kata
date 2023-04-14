package com.example.kata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class WeatherActivity : AppCompatActivity() {

    lateinit var loadingText: TextView
    lateinit var progressBar: ProgressBar

    var index = 0

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

        object : CountDownTimer(30000, 3000) {

            override fun onTick(millisUntilFinished: Long) {

                // Appel de la fonction pour modifier le texte en boucle
                if (index <= (textList.size) - 1) {
                    updateText()
                } else {
                    index = 0
                    updateText()
                }
            }

            override fun onFinish() {
                loadingText.text = "fini !"
            }
        }.start()

    }

    fun updateText() {
        // Si mon index ne dépasse pas la taille de ma liste - 1, je l'incrémente
        loadingText.text = textList[index]
        index++
        Log.d("TIMER", "${loadingText.text}")
        // Sinon, je set l'index à 0 pour recommencer la boucle

    }
}

