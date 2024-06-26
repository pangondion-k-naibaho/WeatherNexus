package com.weathernexus.client.model.dataclass.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    @field:SerializedName("id")
    val id: Int?= 0,

    @field:SerializedName("main")
    val main: String?= "",

    @field:SerializedName("description")
    val description: String?= "",

    @field:SerializedName("icon")
    val icon: String?= ""
):Parcelable