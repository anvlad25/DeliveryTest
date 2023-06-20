package com.example.deliverytest.routing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.deliverytest.ui.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ProfileScreen  : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = ProfileFragment.newInstance()
}