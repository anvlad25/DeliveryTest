package com.example.deliverytest.ui.bag.adapter

interface BagItemViewClickListener {
    fun onItemViewClickPlus(userId: Int, id: Int)
    fun onItemViewClickMinus(userId: Int, id: Int)
}