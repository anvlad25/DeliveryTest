package com.example.deliverytest.routing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.deliverytest.ui.search.SearchFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object SearchScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = SearchFragment.newInstance()
}