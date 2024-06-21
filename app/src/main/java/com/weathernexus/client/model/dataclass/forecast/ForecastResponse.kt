package com.weathernexus.client.model.dataclass.forecast

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.weathernexus.client.model.dataclass.city.City
import com.weathernexus.client.model.dataclass.weather.DetailWeather
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastResponse(
    @field:SerializedName("cod")
    val cod: String?= "",

    @field:SerializedName("message")
    val message: Int= 0,

    @field:SerializedName("cnt")
    val cnt: Int= 0,

    @field:SerializedName("list")
    val list: List<DetailWeather>?= null,

    @field:SerializedName("city")
    val city: City?= null
):Parcelable