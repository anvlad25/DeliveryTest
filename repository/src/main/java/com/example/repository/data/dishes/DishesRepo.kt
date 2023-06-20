package com.example.repository.data.dishes

import io.reactivex.rxjava3.core.Single

interface DishesRepo {
    fun getDishes(): Single<DishesList>
}