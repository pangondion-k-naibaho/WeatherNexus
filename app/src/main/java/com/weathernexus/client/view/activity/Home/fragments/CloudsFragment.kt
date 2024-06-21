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
import com.weathernexus.client.model.Extensions
import com.weathernexus.client.view.activity.Home.CategoriesHomeCommunicator
import com.weathernexus.client.view.adapter.ItemCWAdapter
import com.weathernexus.client.viewmodel.HomeViewModel

class CloudsFragment : Fragment() {
    private val TAG = CloudsFragment::class.java.simpleName
    private lateinit var binding: FragmentWeathersBinding
    private var deliveredCategory: String?= null
    private lateinit var categoriesHomeCommunicator: CategoriesHomeCommunicator
    private val homeViewModel by viewModels<HomeViewModel>()
    private var itemCWAdapter: ItemCWAdapter?= null

    companion object{
        private const val DELIVERED_CATEGORY = "DELIVERED_CATEGORY"

        fun newInstance(deliveryCategory: String?= null): CloudsFragment {
            val fragment = CloudsFragment()
            val bundle = Bundle()
            bundle.putString(DELIVERED_CATEGORY, deliveryCategory)
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
        Log.d(TAG, "deliveredCategory: $deliveredCategory")

        categoriesHomeCommunicator = activity as CategoriesHomeCommunicator

        setUpView()

        return binding.root
    }

    private fun setUpView(){
        homeViewModel.getListCurrentWeather(Extensions.getListCityName())

        homeViewModel.isLoading.observe(this@CloudsFragment.requireActivity(), {
            if(it) categoriesHomeCommunicator.startLoading() else categoriesHomeCommunicator.stopLoading()
        })

        homeViewModel.isFail.observe(this@CloudsFragment.requireActivity(), {
            if(it) Log.d(TAG, "failed")
        })

        homeViewModel.listCurrentWeather.observe(this@CloudsFragment.requireActivity(), {listWeather->
            binding.apply {
                rvItemNews.apply {
                    itemCWAdapter = ItemCWAdapter(listWeather)
                    val myLayoutManager = LinearLayoutManager(this@CloudsFragment.requireActivity())


                    adapter = itemCWAdapter
                    layoutManager = myLayoutManager
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}