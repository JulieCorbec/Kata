package com.example.kata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // Je récupère le texte de chargement
        var loadingText = findViewById<TextView>(R.id.loading_text)


        fun loadingTextChange() {
            var textList: List<String> = listOf(
                "Nous téléchargeons les données...",
                "C'est presque fini...",
                "Plus que quelques secondes avant d'avoir le résultat...")

                var timer = object : CountDownTimer(10000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {

                        for (text in textList) {


                        }
                    }

                    override fun onFinish() {

                    }
                }
            }
        }


            // Je récupère la barre de progression
            var progressBar = findViewById < ProgressBar >(R.id.progress_bar)


        //  }


    }

