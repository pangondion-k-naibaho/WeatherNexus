package com.weathernexus.client.view.activity.Home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.weathernexus.client.R
import com.weathernexus.client.databinding.FragmentWeathersBinding
import com.weathernexus.client.model.Constants
import com.weathernexus.client.model.Constants.CATEGORY_CONSTANTS.Companion.CLOUDS
import com.weathernexus.client.model.Extensions
import com.weathernexus.client.model.Extensions.Companion.sortByFrequency
import com.weathernexus.client.model.Extensions.Companion.sortByFrequencyClear
import com.weathernexus.client.model.Extensions.Companion.sortByFrequencyClouds
import com.weathernexus.client.model.Extensions.Companion.updateFrequencies
import com.weathernexus.client.model.Extensions.Companion.updateFrequency
import com.weathernexus.client.model.Extensions.Companion.updateFrequency2
import com.weathernexus.client.model.Extensions.Companion.updateFrequency3
import com.weathernexus.client.model.dataclass.current_weather.CurrentWeatherResponse
import com.weathernexus.client.view.activity.Home.CategoriesHomeCommunicator
import com.weathernexus.client.view.adapter.ItemCWAdapter
import com.weathernexus.client.viewmodel.HomeViewModel

class CloudsFragment : Fragment() {
    private val TAG = CloudsFragment::class.java.simpleName
    private lateinit var binding: FragmentWeathersBinding
    private var deliveredCategory: String?= null
    private lateinit var deliveredListWeather: ArrayList<CurrentWeatherResponse>
    private lateinit var categoriesHomeCommunicator: CategoriesHomeCommunicator
    private val homeViewModel by viewModels<HomeViewModel>()
    private var itemCWAdapter: ItemCWAdapter?= null

    companion object{
        private const val DELIVERED_CATEGORY = "DELIVERED_CATEGORY"
        private const val DELIVERED_WEATHER = "DELIVERED_WEATHER"

        fun newInstance(deliveryCategory: String?= null, listWeather: ArrayList<CurrentWeatherResponse>): CloudsFragment {
            val fragment = CloudsFragment()
            val bundle = Bundle()
            bundle.putString(DELIVERED_CATEGORY, deliveryCategory)
            bundle.putSerializable(DELIVERED_WEATHER, listWeather)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentWeathersBinding.bind(
            inflater.inflate(
                R.layout.fragment_weathers,
                container,
                false
            )
        )

        deliveredCategory = arguments?.getString(DELIVERED_CATEGORY).toString()
        deliveredListWeather = arguments?.getSerializable(DELIVERED_WEATHER) as ArrayList<CurrentWeatherResponse>
        Log.d(TAG, "deliveredCategory: $deliveredCategory")
        Log.d(TAG, "deliveredListWeather: $deliveredListWeather")

        categoriesHomeCommunicator = activity as CategoriesHomeCommunicator

        setUpView()

        return binding.root
    }

    private fun setUpView(){
        binding.apply {
            val updatedListWeather = deliveredListWeather.sortByFrequencyClouds()
            val rvAdapter = ItemCWAdapter(updatedListWeather,CLOUDS)
            val rvLayoutManager = LinearLayoutManager(this@CloudsFragment.requireActivity())
            rvItemNews.apply {
                adapter = rvAdapter
                layoutManager = rvLayoutManager
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}