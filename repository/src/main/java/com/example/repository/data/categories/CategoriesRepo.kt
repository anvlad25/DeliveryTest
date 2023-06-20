package com.example.repository.data.categories

import io.reactivex.rxjava3.core.Single

interface CategoriesRepo {
    fun getCategories(): Single<CategoriesList>
}