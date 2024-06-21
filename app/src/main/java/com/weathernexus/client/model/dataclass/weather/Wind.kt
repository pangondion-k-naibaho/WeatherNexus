package com.weathernexus.client.model.dataclass.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
    @field:SerializedName("speed")
    val speed: Double?= 0.0,

    @field:SerializedName("deg")
    val deg: Int?= 0,

    @field:SerializedName("gust")
    val gust: Double?= 0.0
):Parcelable