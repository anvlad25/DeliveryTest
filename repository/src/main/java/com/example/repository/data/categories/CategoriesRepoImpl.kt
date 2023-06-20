package com.example.repository.data.categories

import com.example.repository.api.CategoriesApi
import io.reactivex.rxjava3.core.Single

class CategoriesRepoImpl(private val categoriesRepo: CategoriesApi) : CategoriesRepo {
    override fun getCategories(): Single<CategoriesList> =
        categoriesRepo.fetchCategories()
}