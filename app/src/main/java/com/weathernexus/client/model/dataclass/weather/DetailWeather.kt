package com.weathernexus.client.model.dataclass.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailWeather(
    @field:SerializedName("dt")
    val dt: Long?= 0L,

    @field:SerializedName("main")
    val main: MainMeasure?= null,

    @field:SerializedName("weather")
    val weather: List<Weather>?= null,

    @field:SerializedName("clouds")
    val clouds: Clouds?= null,

    @field:SerializedName("wind")
    val wind: Wind?= null,

    @field:SerializedName("visibility")
    val visibility: Int?= 0,

    @field:SerializedName("pop")
    val pop: Double?= 0.0,

    @field:SerializedName("rain")
    val rain: Rain?= null,

    @field:SerializedName("sys")
    val sys: SysWeather?= null,

    @field:SerializedName("dt_txt")
    val dt_txt: String?= ""
):Parcelable