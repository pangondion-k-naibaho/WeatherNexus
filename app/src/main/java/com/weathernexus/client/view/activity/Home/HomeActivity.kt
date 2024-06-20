package com.weathernexus.client.view.activity.Home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.weathernexus.client.R
import com.weathernexus.client.databinding.ActivityHomeBinding
import com.weathernexus.client.model.Constants

class HomeActivity : AppCompatActivity(), CategoriesHomeCommunicator {
    private val TAG = HomeActivity::class.java.simpleName
    private lateinit var binding: ActivityHomeBinding
    private var selectedFragment: Fragment?= null

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
            val weatherCategoryAdapter = CategoryFragmentAdapter(this@HomeActivity)
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