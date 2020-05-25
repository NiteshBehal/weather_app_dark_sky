package com.learning.weatherApp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.learning.weatherApp.R
import com.learning.weatherApp.databinding.ActivityWeatherAppBinding
import com.learning.weatherApp.viewModel.AppActivityViewModel


class WeatherAppActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.fragment_container) }
    private lateinit var viewModel: AppActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityWeatherAppBinding>(
            this,
            R.layout.activity_weather_app
        )
        NavigationUI.setupActionBarWithNavController(this, navController)

        viewModel = ViewModelProvider(this).get(AppActivityViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController
            , null
        )
    }

    override fun onRestart() {
        super.onRestart()

        viewModel.setUpdateFlag(true)
    }



}
