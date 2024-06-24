package com.weathernexus.client.view.activity.Home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLEAR
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLOUDS
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.RAIN
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.SNOW
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse
import com.weathernexus.client.view.activity.Home.fragments.ClearFragment
import com.weathernexus.client.view.activity.Home.fragments.CloudsFragment
import com.weathernexus.client.view.activity.Home.fragments.RainFragment
import com.weathernexus.client.view.activity.Home.fragments.SnowFragment

class CategoryFragmentAdapter(
    activity: AppCompatActivity,
    listWeatherClear: ArrayList<CurrentWeatherResponse>,
    listWeatherClouds: ArrayList<CurrentWeatherResponse>,
    listWeatherRain: ArrayList<CurrentWeatherResponse>,
    listWeatherSnow: ArrayList<CurrentWeatherResponse>,
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    val listCurrentWeatherClear = listWeatherClear
    val listCurrentWeatherClouds = listWeatherClouds
    val listCurrentWeatherRain = listWeatherRain
    val listCurrentWeatherSnow = listWeatherSnow

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment?= null
        when(position){
            0 -> fragment = ClearFragment.newInstance(CLEAR, listCurrentWeatherClear)
            1 -> fragment = CloudsFragment.newInstance(CLOUDS, listCurrentWeatherClouds)
            2 -> fragment = RainFragment.newInstance(RAIN, listCurrentWeatherRain)
            3 -> fragment = SnowFragment.newInstance(SNOW, listCurrentWeatherSnow)
        }

        return fragment as Fragment
    }

}