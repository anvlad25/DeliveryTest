package com.example.repository.data.dishes

import com.google.gson.annotations.SerializedName

data class DishesList(
    @SerializedName("dishes")
    val dishesList: List<Dish>
)