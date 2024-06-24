package com.weathernexus.client.view.activity.Home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.weathernexus.client.R
import com.weathernexus.client.databinding.ActivityHomeBinding
import com.weathernexus.client.model.Constants
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLEAR
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLOUDS
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.RAIN
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.SNOW
import com.weathernexus.client.model.Extensions.Companion.getListCityId
import com.weathernexus.client.model.Extensions.Companion.getListCityName
import com.weathernexus.client.model.Extensions.Companion.sortByFrequency
import com.weathernexus.client.model.Extensions.Companion.updateFrequencies2
import com.weathernexus.client.model.Extensions.Companion.updateFrequenciesClear
import com.weathernexus.client.model.Extensions.Companion.updateFrequenciesCloud
import com.weathernexus.client.model.Extensions.Companion.updateFrequenciesRain
import com.weathernexus.client.model.Extensions.Companion.updateFrequenciesSnow
import com.weathernexus.client.model.Extensions.Companion.updateFrequency
import com.weathernexus.client.model.Extensions.Companion.updateFrequencyClear
import com.weathernexus.client.model.Extensions.Companion.updateFrequencyClouds
import com.weathernexus.client.model.Extensions.Companion.updateFrequencyRain
import com.weathernexus.client.model.Extensions.Companion.updateFrequencySnow
import com.weathernexus.client.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity(), CategoriesHomeCommunicator {
    private val TAG = HomeActivity::class.java.simpleName
    private lateinit var binding: ActivityHomeBinding
    private var selectedFragment: Fragment?= null
    private val homeViewModel by viewModels<HomeViewModel>()

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView(){
        binding.apply {

            homeViewModel.getListForecast(getListCityId())

            homeViewModel.getListCurrentWeather(getListCityName())

            homeViewModel.isLoading.observe(this@HomeActivity, {
                if(it) setForLoading(true) else setForLoading(false)
            })

            homeViewModel.isFail.observe(this@HomeActivity, {
                Log.d(TAG, "isFail")
            })

            homeViewModel.listForecastCity.observe(this@HomeActivity, {listForecast->
                homeViewModel.listCurrentWeather.observe(this@HomeActivity, {listWeather->
                    val updatedListWeather = listWeather.updateFrequency(listForecast)

                    val updatedListWeatherClear = listWeather.updateFrequencyClear(listForecast)
                    val updatedListWeatherClouds = listWeather.updateFrequencyClouds(listForecast)
                    val updatedListWeatherRain = listWeather.updateFrequencyRain(listForecast)
                    val updatedListWeatherSnow = listWeather.updateFrequencySnow(listForecast)

                    val weatherCategoryAdapter = CategoryFragmentAdapter(this@HomeActivity, updatedListWeatherClear, updatedListWeatherClouds, updatedListWeatherRain, updatedListWeatherSnow)
                    vpCategoryWeather.apply {
                        adapter = weatherCategoryAdapter
                        registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                            override fun onPageSelected(position: Int) {
                                super.onPageSelected(position)
                                selectedFragment = supportFragmentManager.findFragmentByTag("f$position")
                            }
                        })
                    }

                    val categoryTitleArray : Array<String> = Constants.CATEGORY_CONSTANTS.Companion.WEATHER.values().map { it.name.toLowerCase().capitalize() }.toTypedArray()

                    TabLayoutMediator(tlCategoryWeather, vpCategoryWeather, false, true){tab, position->
                        tab.text = categoryTitleArray[position]
                    }.attach()

                })
            })

//            val weatherCategoryAdapter = CategoryFragmentAdapter(this@HomeActivity)
//            vpCategoryWeather.apply {
//                adapter = weatherCategoryAdapter
//                registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
//                    override fun onPageSelected(position: Int) {
//                        super.onPageSelected(position)
//                        selectedFragment = supportFragmentManager.findFragmentByTag("f$position")
//                    }
//                })
//            }
//
//            val categoryTitleArray : Array<String> = Constants.CATEGORY_CONSTANTS.Companion.WEATHER.values().map { it.name.toLowerCase().capitalize() }.toTypedArray()
//
//            TabLayoutMediator(tlCategoryWeather, vpCategoryWeather, false, true){tab, position->
//                tab.text = categoryTitleArray[position]
//            }.attach()
        }
    }

    private fun setForLoading(isLoading: Boolean){
        if(isLoading){
            binding.loadingLayout.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.VISIBLE
        }else{
            binding.loadingLayout.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE
        }
    }

    private fun setForPopUpDisplaying(isDisplaying: Boolean){
        if(isDisplaying){
            binding.loadingLayout.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.GONE
        }else{
            binding.loadingLayout.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE
        }
    }

    override fun startLoading() {
        setForLoading(true)
    }

    override fun stopLoading() {
        setForLoading(false)
    }
}