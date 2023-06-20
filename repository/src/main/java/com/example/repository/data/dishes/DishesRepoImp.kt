package com.example.repository.data.dishes

import com.example.repository.api.DishesApi
import io.reactivex.rxjava3.core.Single

class DishesRepoImp(private val dishesRepo: DishesApi) : DishesRepo {
    override fun getDishes(): Single<DishesList> =
        dishesRepo.fetchDishes()
}