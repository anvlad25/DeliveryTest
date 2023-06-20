package com.example.repository.data.categories

import com.google.gson.annotations.SerializedName

data class CategoriesList(
    @SerializedName("сategories")
    val categoriesList: List<Category>
)