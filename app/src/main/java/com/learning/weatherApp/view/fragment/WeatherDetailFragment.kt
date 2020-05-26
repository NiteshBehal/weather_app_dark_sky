package com.learning.weatherApp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.learning.weatherApp.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment() {

    private lateinit var binding: FragmentWeatherDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@WeatherDetailFragment
                weatherInfo = WeatherDetailFragmentArgs.fromBundle(requireArguments()).weatherInfo
            }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}
