package com.weathernexus.client.view.activity.LoadScreen

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.weathernexus.client.R
import com.weathernexus.client.databinding.ActivityLoadScreenBinding
import com.weathernexus.client.view.activity.Home.HomeActivity

class LoadScreenActivity : Activity() {
    private val TAG = LoadScreenActivity::class.java.simpleName
    private lateinit var binding: ActivityLoadScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{false}
        actionBar?.hide()
        setUpView()
    }

    private fun setUpView(){
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            startActivity(HomeActivity.newIntent(this@LoadScreenActivity))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 4000)
    }
}
