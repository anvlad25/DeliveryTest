package com.example.deliverytest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repository.data.categories.CategoriesList
import com.example.repository.data.categories.CategoriesRepo
import com.example.repository.data.categories.Category
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(val categoriesRepo: CategoriesRepo) : ViewModel() {
    private val _liveData = MutableLiveData<CategoriesList>()
    val liveData: MutableLiveData<CategoriesList> get() = _liveData

    val test = categoriesRepo.getCategories()
    val asd = "categoriesRepo.getCategories()"

    fun getData() {
        categoriesRepo
            .getCategories()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _liveData.postValue(it)
                }, {}
            )
    }
}