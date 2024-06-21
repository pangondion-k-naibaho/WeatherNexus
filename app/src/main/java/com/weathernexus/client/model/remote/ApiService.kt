package com.weathernexus.client.model.remote

import com.weathernexus.client.model.Constants.KEY.Companion.API_KEY
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse
import com.weathernexus.client.model.dataclass.forecast.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("id")id: String,
        @Query("appid") appId: String = API_KEY
    ): Call<ForecastResponse>

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("q")cityName: String,
        @Query("appid") appId: String = API_KEY
    ):Call<CurrentWeatherResponse>
}