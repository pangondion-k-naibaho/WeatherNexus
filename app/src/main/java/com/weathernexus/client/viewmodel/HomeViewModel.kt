package com.weathernexus.client.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weathernexus.client.model.Extensions.Companion.replaceSpaces
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse
import com.weathernexus.client.model.remote.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail:LiveData<Boolean> = _isFail

    private var _listCurrentWeather = MutableLiveData<ArrayList<CurrentWeatherResponse>>()
    val listCurrentWeather: LiveData<ArrayList<CurrentWeatherResponse>> = _listCurrentWeather

    fun getListCurrentWeather(inputs: List<String>){
        _isLoading.value = true

        val currentList = _listCurrentWeather.value ?: ArrayList()

        inputs.forEach {input ->
            val inputCity = if(input.contains(" ")){
                input.replaceSpaces()
            }else{
                input
            }

            val client = ApiConfig.getApiService().getCurrentWeather(inputCity)
            client.enqueue(object: retrofit2.Callback<CurrentWeatherResponse>{
                override fun onResponse(
                    call: Call<CurrentWeatherResponse>,
                    response: Response<CurrentWeatherResponse>
                ) {
                    _isLoading.value = false
                    if(response.isSuccessful){
                        _isFail.value = false
                        val weatherResponse = response.body()
                        weatherResponse?.let {
                            currentList.add(it)
                            _listCurrentWeather.postValue(currentList)
                        }
                    }else{
                        _isFail.value = true
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                    _isLoading.value = false
                    _isFail.value = true
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }

            })
        }
    }
}