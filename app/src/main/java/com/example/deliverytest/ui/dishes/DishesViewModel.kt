package com.example.deliverytest.ui.dishes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repository.data.dishes.DishesList
import com.example.repository.data.dishes.DishesRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class DishesViewModel(val dishesRepo: DishesRepo) : ViewModel() {
    private val _liveData = MutableLiveData<DishesList>()
    val liveData: MutableLiveData<DishesList> get() = _liveData

    fun getData() {
        dishesRepo
            .getDishes()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _liveData.postValue(it)
                }, {}
            )
    }
}