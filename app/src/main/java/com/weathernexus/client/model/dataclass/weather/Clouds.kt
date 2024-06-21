package com.weathernexus.client.model.dataclass.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(
    @field:SerializedName("all")
    val all: Int?= 0
):Parcelable