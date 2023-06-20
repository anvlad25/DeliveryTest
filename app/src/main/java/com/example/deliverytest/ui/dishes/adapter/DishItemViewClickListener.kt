package com.example.deliverytest.ui.dishes.adapter

import com.example.repository.data.dishes.Dish

interface DishItemViewClickListener {
    fun onItemViewClick(dish: Dish)
}