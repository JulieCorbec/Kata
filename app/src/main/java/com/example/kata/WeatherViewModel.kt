package com.example.kata


import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kata.api.ApiClient
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    var weatherListValue = MutableLiveData<MutableList<Weather>>()

    var progressValue = MutableLiveData<Int>()

    var textValue = MutableLiveData<String>()

    var weatherList = mutableListOf<Weather>()

    var isTimerFinished: MutableLiveData<Boolean> = MutableLiveData(false)

    private var textList: List<String> = listOf(
        "C'est presque fini...",
        "Plus que quelques secondes avant d'avoir le résultat...",
        "Nous téléchargeons les données..."
    )

    var indexText = 0
    var indexCity = 0
    var i = 0
    val timeInMilliSecond = 60000L

    val API_KEY = "059d75c39f3d4fd6ce01fc6215fa515c"

    var citiesList: List<String> = listOf(
        "Rennes",
        "Paris",
        "Nantes",
        "Bordeaux",
        "Lyon",
    )


    var timer = object : CountDownTimer(timeInMilliSecond, 1000) {

        override fun onTick(millisUntilFinished: Long) {

            i += (100 / 60)
            progressValue.value = i

            val secondUtilFinished = (millisUntilFinished / 1000)

            if (secondUtilFinished % 6 == 0L) {
                // Appel de la fonction pour modifier le texte en boucle
                if (indexText <= (textList.size) - 1) {
                    updateText()
                } else {
                    indexText = 0
                    updateText()
                }
                Log.d("TEST_MESSAGE", textValue.toString())
            }

            if (secondUtilFinished % 10 == 0L) {
                weatherCallByCity()
                Log.d("TEST_WEATHER", weatherListValue.toString())
            }
            Log.d("TEST_SECONDS", secondUtilFinished.toString())

        }

        override fun onFinish() {
            weatherListValue.value = weatherList
            isTimerFinished.value = true

        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////////////////////////

    init {
        timer.start()
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTION
    ///////////////////////////////////////////////////////////////////////////


    fun updateText() {
        // Si mon index ne dépasse pas la taille de ma liste - 1, je l'incrémente
        textValue.value = textList[indexText]
        indexText++
    }


    fun weatherCallByCity() {
        if (indexCity <= (citiesList.size) - 1) {
            executeCall()
            indexCity++
        }
    }

    private fun executeCall() {

        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getWeather(citiesList[indexCity], API_KEY)
                val content = response.body()
                if (response.isSuccessful && content != null) {
                    weatherList.add(content)
                    Log.d("WEATHER", content.toString())
                } else {
                 /*   Toast.makeText(
                        this,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()*/

                }

            } catch (e: Exception) {
               /* Toast.makeText(
                    this@WeatherViewModel,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()*/
            }
        }
    }


}