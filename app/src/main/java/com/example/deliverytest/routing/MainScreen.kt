package com.example.deliverytest.routing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.deliverytest.ui.main.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object MainScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = MainFragment.newInstance()
}