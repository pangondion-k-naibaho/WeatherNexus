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
import com.weathernexus.client.model.Extensions.Companion.getListCityName
import com.weathernexus.client.view.activity.Home.CategoriesHomeCommunicator
import com.weathernexus.client.view.adapter.ItemCWAdapter
import com.weathernexus.client.viewmodel.HomeViewModel

class ClearFragment : Fragment() {
    private val TAG = ClearFragment::class.java.simpleName
    private lateinit var binding: FragmentWeathersBinding
    private var deliveredCategory: String?= null
    private lateinit var categoriesHomeCommunicator: CategoriesHomeCommunicator
    private val homeViewModel by viewModels<HomeViewModel>()
    private var itemCWAdapter: ItemCWAdapter?= null

    companion object{
        private const val DELIVERED_CATEGORY = "DELIVERED_CATEGORY"

        fun newInstance(deliveryCategory: String?= null): ClearFragment {
            val fragment = ClearFragment()
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
        homeViewModel.getListCurrentWeather(getListCityName())

        homeViewModel.isLoading.observe(this@ClearFragment.requireActivity(), {
            if(it) categoriesHomeCommunicator.startLoading() else categoriesHomeCommunicator.stopLoading()
        })

        homeViewModel.isFail.observe(this@ClearFragment.requireActivity(), {
            if(it) Log.d(TAG, "failed")
        })

        homeViewModel.listCurrentWeather.observe(this@ClearFragment.requireActivity(), {listWeather->
            binding.apply {
                rvItemNews.apply {
                    itemCWAdapter = ItemCWAdapter(listWeather)
                    val myLayoutManager = LinearLayoutManager(this@ClearFragment.requireActivity())


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