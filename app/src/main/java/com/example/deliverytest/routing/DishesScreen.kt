package com.example.deliverytest.routing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.repository.data.categories.Category
import com.example.deliverytest.ui.dishes.DishesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class DishesScreen(private val category: Category) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = DishesFragment.newInstance(category)
}