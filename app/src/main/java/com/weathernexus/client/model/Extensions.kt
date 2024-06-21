package com.weathernexus.client.model

import android.content.Context
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse
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

        fun ArrayList<CurrentWeatherResponse>.sortByFrequency(){
            this.sortBy { it.frequency }
        }
    }

}