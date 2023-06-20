package com.example.repository.api

import com.example.repository.data.categories.CategoriesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CategoriesApi {
    @GET("/v3/058729bd-1402-4578-88de-265481fd7d54")
    fun fetchCategories(): Single<CategoriesList>
}