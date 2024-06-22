package com.weathernexus.client.model.dataclass.current_weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.weathernexus.client.model.dataclass.city.CoordCity
import com.weathernexus.client.model.dataclass.weather.Clouds
import com.weathernexus.client.model.dataclass.weather.MainMeasure
import com.weathernexus.client.model.dataclass.weather.Rain
import com.weathernexus.client.model.dataclass.weather.SysWeather
import com.weathernexus.client.model.dataclass.weather.Weather
import com.weathernexus.client.model.dataclass.weather.Wind
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeatherResponse(
    @field:SerializedName("coord")
    val coordCity: CoordCity?= null,

    @field:SerializedName("weather")
    val weather: List<Weather>?= null,

    @field:SerializedName("base")
    val base: String?= "",

    @field:SerializedName("main")
    val main: MainMeasure?= null,

    @field:SerializedName("visibility")
    val visibility: Int?= 0,

    @field:SerializedName("wind")
    val wind: Wind?= null,

    @field:SerializedName("rain")
    val rain: Rain?= null,

    @field:SerializedName("clouds")
    val clouds: Clouds?= null,

    @field:SerializedName("dt")
    val dt: Long?= 0L,

    @field:SerializedName("sys")
    val sys: SysWeather?= null,

    @field:SerializedName("timezone")
    val timezone: Int?= 0,

    @field:SerializedName("id")
    val id: Long?= 0L,

    @field:SerializedName("name")
    val name: String?= "",

    @field:SerializedName("cod")
    val cod: Int?= 0,

    var frequencyClear: Int?= 0,
    var frequencyClouds: Int?= 0,
    var frequencyRain: Int?= 0,
    var frequencySnow: Int?= 0,
):Parcelable