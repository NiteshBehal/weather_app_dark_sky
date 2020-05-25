package com.learning.weatherApp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.learning.weatherApp.R
import com.learning.weatherApp.databinding.FragmentLocationListBinding
import com.learning.weatherApp.repository.network.api.NetworkState
import com.learning.weatherApp.repository.vo.WeatherInfo
import com.learning.weatherApp.view.adapter.LocationListAdapter
import com.learning.weatherApp.viewModel.AppActivityViewModel
import com.learning.weatherApp.viewModel.LocationListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_location_list.*

/**
 * A simple [Fragment] subclass.
 */
class LocationListFragment : Fragment() {
    private lateinit var viewModel: LocationListFragmentViewModel
    private lateinit var activityViewModel: AppActivityViewModel
    private lateinit var binding: FragmentLocationListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@LocationListFragment

            }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.refresh_menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_weather_info -> activityViewModel.setUpdateFlag(true)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityViewModel =
            ViewModelProvider(requireActivity()).get(AppActivityViewModel::class.java)
        viewModel = ViewModelProvider(this).get(LocationListFragmentViewModel::class.java).also {
            binding.locationListViewModel = it
        }
        binding.rvLocationList.apply {
            adapter = LocationListAdapter(listOf())
        }

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
        })

        setObserverForActivityRestart();
        fetchWeatherInfoList()

    }

    fun fetchWeatherInfoList() {
        viewModel.weatherInfoList.observe(viewLifecycleOwner, Observer {
            (binding.rvLocationList.adapter as LocationListAdapter).updateList(it)
        })
        if (viewModel.listIsEmpty() && (activityViewModel.getUpdateFlag().value == null || activityViewModel.getUpdateFlag()?.value == false)) {
            viewModel.fetchWeatherInfo()
        }
    }

    private fun setObserverForActivityRestart() {
        activityViewModel.getUpdateFlag().observe(requireActivity(), Observer {
            if (it && viewModel.networkState.value != NetworkState.LOADING) {
                activityViewModel.setUpdateFlag(false)
                viewModel.fetchWeatherInfo()
            }
        })
    }
}
