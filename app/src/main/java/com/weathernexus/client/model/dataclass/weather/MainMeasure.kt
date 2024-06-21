package com.weathernexus.client.model.dataclass.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainMeasure(
    @field:SerializedName("temp")
    val temp: Double? = 0.0,

    @field:SerializedName("feels_like")
    val feels_like: Double?= 0.0,

    @field:SerializedName("temp_min")
    val temp_min: Double?= 0.0,

    @field:SerializedName("temp_max")
    val temp_max: Double?= 0.0,

    @field:SerializedName("pressure")
    val pressure: Int?= 0,

    @field:SerializedName("sea_level")
    val sea_level: Int?= 0,

    @field:SerializedName("grnd_level")
    val grnd_level: Int?= 0,

    @field:SerializedName("humidity")
    val humidity: Int?= 0,

    @field:SerializedName("temp_kf")
    val temp_kf: Double?= 0.0
):Parcelable