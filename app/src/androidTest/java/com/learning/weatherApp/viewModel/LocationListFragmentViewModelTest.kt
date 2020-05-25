package com.learning.weatherApp.viewModel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

class LocationListFragmentViewModelTest {

    private lateinit var viewModel: LocationListFragmentViewModel

    @Before
    fun setUp() {
        viewModel = LocationListFragmentViewModel()
    }

    @Test
    fun getLocationListTest() {
        assertEquals(2, viewModel.getLocationList().size)
    }

    @Test
    fun listIsEmptyTest() {
        assertTrue(viewModel.listIsEmpty())
    }
}