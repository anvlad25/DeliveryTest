package com.example.deliverytest.ui.main.adapter

import com.example.repository.data.categories.Category

interface CategoryItemViewClickListener {
    fun onItemViewClick(category: Category)
}