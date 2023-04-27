package com.example.kata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kata.recyclerview.WeatherAdapter

class WeatherActivity : AppCompatActivity() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////


    lateinit var weatherViewModel: WeatherViewModel
    lateinit var loadingText: TextView
    lateinit var progressBar: ProgressBar
    lateinit var customAdapter: WeatherAdapter
    lateinit var btnAgain: Button

    var weatherList = mutableListOf<Weather>()
    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        loadingText = findViewById(R.id.loading_text)
        progressBar = findViewById(R.id.progress_bar)
        btnAgain = findViewById(R.id.btn_again)
        val btnAgainLayout = findViewById<LinearLayout>(R.id.btn_again_layout)


        val recyclerView: RecyclerView = findViewById(R.id.rv_weather)
        customAdapter = WeatherAdapter(this, weatherList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        //observe changes and update viewModel

        weatherViewModel.progressValue.observe(this) { progressValue ->
            progressBar.progress = progressValue
        }

        weatherViewModel.textValue.observe(this) { textValue ->
            loadingText.text = textValue

        }

        weatherViewModel.weatherListValue.observe(this) { weatherListValue ->
            weatherList = weatherListValue
            customAdapter = WeatherAdapter(this, weatherList)
            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = customAdapter
        }

        weatherViewModel.isTimerFinished.observe(this) { isFinished ->
            recyclerView.isVisible = isFinished
            btnAgainLayout.isVisible = isFinished
            loadingText.isVisible = !isFinished
            progressBar.isVisible = !isFinished

        }

        btnAgain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            weatherList.clear()
        }

    }


    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE FUNCTION
    ///////////////////////////////////////////////////////////////////////////


}

