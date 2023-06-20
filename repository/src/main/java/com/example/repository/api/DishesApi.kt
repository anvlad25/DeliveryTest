package com.example.repository.api

import com.example.repository.data.dishes.DishesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DishesApi {
    @GET("/v3/aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    fun fetchDishes(): Single<DishesList>
}