package com.weathernexus.client.model.dataclass.city

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    @field:SerializedName("id")
    val id: Long? = 0L,

    @field:SerializedName("name")
    val name: String?= "",

    @field:SerializedName("coord")
    val coord: CoordCity?= null,

    @field:SerializedName("country")
    val country: String?= "",

    @field:SerializedName("population")
    val population: Long? = 0L,

    @field:SerializedName("timezone")
    val timezone: Int? = 0,

    @field:SerializedName("sunrise")
    val sunrise: Long?= 0L,

    @field:SerializedName("sunset")
    val sunset: Long?= 0L
):Parcelable