package com.example.repository.data.categories

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int = 1,

    @SerializedName("name")
    val name: String = "name",

    @SerializedName("image_url")
    val image_url: String = "image_url"
)