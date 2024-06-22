package com.weathernexus.client.model

import android.content.Context
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLEAR
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLOUDS
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.RAIN
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.SNOW
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse
import com.weathernexus.client.model.dataclass.forecast.ForecastResponse
import java.text.SimpleDateFormat
import java.util.Locale

class Extensions {
    companion object{
        fun Int.dpToPx(context: Context): Int {
            val scale = context.resources.displayMetrics.density
            return (this * scale + 0.5f).toInt()
        }
        fun String.formatDate(): String {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
            val date = inputFormat.parse(this)
            return outputFormat.format(date!!)
        }
        fun Double.kelvinToCelsius(): Double {
            return this - 273.15
        }

        fun String.replaceSpaces(): String {
            return this.replace(" ", "%20")
        }

        fun getListCityId(): List<Int> = listOf(
            Constants.ID_CITY_CONSTANTS.LAKE_ZURICH,
            Constants.ID_CITY_CONSTANTS.UPPER_HUNT,
            Constants.ID_CITY_CONSTANTS.DAVOS,
            Constants.ID_CITY_CONSTANTS.ALASKA,
            Constants.ID_CITY_CONSTANTS.SAHARA_VILLAGE,
            Constants.ID_CITY_CONSTANTS.SANDY_HILLS,
            Constants.ID_CITY_CONSTANTS.BELGRADE,
            Constants.ID_CITY_CONSTANTS.CALIFORNIA)

        fun getListCityName():List<String> = listOf(
            Constants.NAME_CITY_CONSTANTS.LAKE_ZURICH,
            Constants.NAME_CITY_CONSTANTS.UPPER_HUNT,
            Constants.NAME_CITY_CONSTANTS.DAVOS,
            Constants.NAME_CITY_CONSTANTS.ALASKA,
            Constants.NAME_CITY_CONSTANTS.SAHARA_VILLAGE,
            Constants.NAME_CITY_CONSTANTS.SANDY_HILLS,
            Constants.NAME_CITY_CONSTANTS.BELGRADE,
            Constants.NAME_CITY_CONSTANTS.CALIFORNIA
        )

        fun Double.roundToTwoDecimalPlaces(): Double {
            return String.format("%.2f", this).toDouble()
        }

        fun ArrayList<CurrentWeatherResponse>.sortByFrequency(weatherCondition: String): ArrayList<CurrentWeatherResponse>{
            when(weatherCondition){
                CLEAR -> this.sortByDescending { it.frequencyClear }
                CLOUDS -> this.sortByDescending { it.frequencyClouds }
                RAIN -> this.sortByDescending { it.frequencyRain }
                SNOW -> this.sortByDescending { it.frequencySnow }
            }

            return this
        }

        fun ArrayList<CurrentWeatherResponse>.sortByFrequencyClear(): ArrayList<CurrentWeatherResponse>{
            this.sortByDescending { it.frequencyClear }
            return this
        }

        fun ArrayList<CurrentWeatherResponse>.updateFrequencies_e2(listForecast: ArrayList<ForecastResponse>):ArrayList<CurrentWeatherResponse>{
            for(i in this.indices){
                for(j in listForecast.indices){
                    if(this[i].name == listForecast[j].city!!.name){
                        for(k in listForecast[j].list!!.indices){
                            for(l in listForecast[j].list!![k].weather!!.indices){
                                when(listForecast[j].list!![k].weather!![l].main){
                                    CLOUDS -> this[i].frequencyClouds = this[i].frequencyClouds!! + 1
                                    CLEAR -> this[i].frequencyClear = this[i].frequencyClear!! + 1
                                    RAIN -> this[i].frequencyRain = this[i].frequencyRain!! + 1
                                    SNOW -> this[i].frequencySnow = this[i].frequencySnow!! + 1
                                }
                            }
                        }
                    }
                }
            }

            return this
        }

        fun ArrayList<CurrentWeatherResponse>.updateFrequency(listForecast: ArrayList<ForecastResponse>):ArrayList<CurrentWeatherResponse> {
            // Iterasi melalui setiap item di listWeather (this merujuk pada ArrayList<CurrentWeatherResponse>)
            this.forEach { currentWeather ->
                // Iterasi melalui setiap item di listForecast
                listForecast.forEach { forecast ->
                    // Cek apakah nama kota sama
                    if (forecast.city?.name == currentWeather.name) {
                        // Iterasi setiap detail cuaca dalam forecast.list
                        forecast.list?.forEach { detailWeather ->
                            // Cek apakah kondisi cuaca sesuai dengan yang diinginkan
                            when{
                                (detailWeather.weather?.any { it.main == CLEAR } == true) -> currentWeather.frequencyClear = currentWeather.frequencyClear!! + 1
                                (detailWeather.weather?.any { it.main == CLOUDS } == true) -> currentWeather.frequencyClouds = currentWeather.frequencyClouds!! + 1
                                (detailWeather.weather?.any { it.main == RAIN } == true) -> currentWeather.frequencyRain = currentWeather.frequencyRain!! + 1
                                (detailWeather.weather?.any { it.main == SNOW } == true) -> currentWeather.frequencySnow = currentWeather.frequencySnow!! + 1
                            }
                        }
                    }
                }
            }

            return this
        }

        fun ArrayList<CurrentWeatherResponse>.updateFrequency2(
            listForecast: ArrayList<ForecastResponse>,
            weatherCondition: String
        ): ArrayList<CurrentWeatherResponse> {
            // Iterasi melalui setiap item di listWeather (this merujuk pada ArrayList<CurrentWeatherResponse>)
            this.forEach { currentWeather ->
                // Iterasi melalui setiap item di listForecast
                listForecast.forEach { forecast ->
                    // Cek apakah nama kota sama
                    if (forecast.city?.name == currentWeather.name) {
                        // Iterasi setiap detail cuaca dalam forecast.list
                        forecast.list?.forEach { detailWeather ->
                            // Cek apakah kondisi cuaca sesuai dengan yang diinginkan
                            if (detailWeather.weather?.any { it.main == weatherCondition } == true) {
                                // Jika kondisi terpenuhi, tambahkan frequency
                                when(weatherCondition){
                                    CLEAR -> currentWeather.frequencyClear = (currentWeather.frequencyClear ?: 0) + 1
                                    CLOUDS -> currentWeather.frequencyClouds = (currentWeather.frequencyClouds ?: 0) + 1
                                    RAIN -> currentWeather.frequencyRain = (currentWeather.frequencyRain ?: 0) + 1
                                    SNOW -> currentWeather.frequencySnow = (currentWeather.frequencySnow ?: 0) + 1
                                }

                            }
                        }
                    }
                }
            }
            // Mengembalikan ArrayList<CurrentWeatherResponse> yang telah diperbarui
            return this
        }

        fun ArrayList<CurrentWeatherResponse>.updateFrequency3(listForecast: ArrayList<ForecastResponse>, weatherCondition: String):ArrayList<CurrentWeatherResponse> {
            // Iterasi melalui setiap item di listWeather (this merujuk pada ArrayList<CurrentWeatherResponse>)
            this.forEach { currentWeather ->
                // Iterasi melalui setiap item di listForecast
                listForecast.forEach { forecast ->
                    // Cek apakah nama kota sama
                    if (forecast.city?.name == currentWeather.name) {
                        // Iterasi setiap detail cuaca dalam forecast.list
                        forecast.list?.forEach { detailWeather ->
                            // Cek apakah kondisi cuaca sesuai dengan yang diinginkan
                            when{
                                (detailWeather.weather?.any { it.main == CLEAR && weatherCondition == CLEAR } == true) -> currentWeather.frequencyClear = currentWeather.frequencyClear!! + 1
                                (detailWeather.weather?.any { it.main == CLOUDS && weatherCondition == CLOUDS} == true) -> currentWeather.frequencyClouds = currentWeather.frequencyClouds!! + 1
                                (detailWeather.weather?.any { it.main == RAIN && weatherCondition == RAIN} == true) -> currentWeather.frequencyRain = currentWeather.frequencyRain!! + 1
                                (detailWeather.weather?.any { it.main == SNOW && weatherCondition == SNOW} == true) -> currentWeather.frequencySnow = currentWeather.frequencySnow!! + 1
                            }
                        }
                    }
                }
            }

            return this
        }

        fun ArrayList<CurrentWeatherResponse>.updateFrequencies(
            forecastResponses: ArrayList<ForecastResponse>,
            weatherCondition: String
        ): ArrayList<CurrentWeatherResponse> {
            for (currentWeather in this) {
                val matchingForecast = forecastResponses.find { forecast ->
                    forecast.city?.name == currentWeather.name
                }

                matchingForecast?.list?.forEach { detailWeather ->
                    if (detailWeather.weather!!.firstOrNull()?.main == weatherCondition) {
                        when (weatherCondition) {
                            CLOUDS -> currentWeather.frequencyClouds = (currentWeather.frequencyClouds ?: 0) + 1
                            CLEAR -> currentWeather.frequencyClear = (currentWeather.frequencyClear ?: 0) + 1
                            RAIN -> currentWeather.frequencyRain = (currentWeather.frequencyRain ?: 0) + 1
                            SNOW -> currentWeather.frequencySnow = (currentWeather.frequencySnow ?: 0) + 1
                        }
                    }
                }
            }
            return this
        }

        fun ArrayList<CurrentWeatherResponse>.updateFrequencies2(forecastResponses: ArrayList<ForecastResponse>): ArrayList<CurrentWeatherResponse> {
            for (currentWeather in this) {
                val matchingForecast = forecastResponses.find { forecast ->
                    forecast.city?.name == currentWeather.name
                }

                matchingForecast?.list?.forEach { detailWeather ->
                    when (detailWeather.weather!!.firstOrNull()?.main) {
                        CLOUDS -> currentWeather.frequencyClouds = (currentWeather.frequencyClouds ?: 0) + 1
                        CLEAR -> currentWeather.frequencyClear = (currentWeather.frequencyClear ?: 0) + 1
                        RAIN -> currentWeather.frequencyRain = (currentWeather.frequencyRain ?: 0) + 1
                        SNOW -> currentWeather.frequencySnow = (currentWeather.frequencySnow ?: 0) + 1
                    }
                }
            }
            return this
        }

    }

}