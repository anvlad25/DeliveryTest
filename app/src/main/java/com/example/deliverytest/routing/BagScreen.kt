package com.example.deliverytest.routing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.deliverytest.ui.bag.BagFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object BagScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = BagFragment.newInstance()
}