package com.weathernexus.client.model.dataclass.city

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoordCity(
    @field:SerializedName("lat")
    val lat: Double = 0.0,

    @field:SerializedName("lon")
    val lon: Double = 0.0
):Parcelable