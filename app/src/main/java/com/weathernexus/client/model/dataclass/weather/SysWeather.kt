package com.weathernexus.client.model.dataclass.weather

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SysWeather(
    @field:SerializedName("pod")
    val pod: String?= ""
):Parcelable