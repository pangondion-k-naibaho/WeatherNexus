package com.weathernexus.client.view.activity.Home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weathernexus.client.R
import com.weathernexus.client.databinding.FragmentWeathersBinding
import com.weathernexus.client.view.activity.Home.CategoriesHomeCommunicator

class CloudsFragment : Fragment() {
    private val TAG = CloudsFragment::class.java.simpleName
    private lateinit var binding: FragmentWeathersBinding
    private var deliveredCategory: String?= null
    private lateinit var categoriesHomeCommunicator: CategoriesHomeCommunicator

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

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}