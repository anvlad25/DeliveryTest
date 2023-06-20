package com.example.repository.data.dishes

import com.google.gson.annotations.SerializedName

data class Dish(
    @SerializedName("id")
    val id: Int = 1,

    @SerializedName("name")
    val name: String = "name",

    @SerializedName("price")
    val price: Int = 0,

    @SerializedName("weight")
    val weight: Int = 0,

    @SerializedName("description")
    val description: String = "description",

    @SerializedName("image_url")
    val image_url: String = "image_url",

    @SerializedName("tegs")
    val tegs: List<String> = listOf("tegs")
)