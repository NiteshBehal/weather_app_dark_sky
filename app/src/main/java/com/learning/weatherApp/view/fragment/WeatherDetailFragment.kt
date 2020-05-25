package com.learning.weatherApp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater

import com.learning.weatherApp.R
import com.learning.weatherApp.databinding.FragmentWeatherDetailBinding

/**
 * A simple [Fragment] subclass.
 */
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
