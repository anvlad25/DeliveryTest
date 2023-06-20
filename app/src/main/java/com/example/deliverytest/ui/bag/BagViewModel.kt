package com.example.deliverytest.ui.bag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repository.data.room.BagDishesDao
import com.example.repository.data.room.BagDishesEntity
import io.reactivex.rxjava3.schedulers.Schedulers

class BagViewModel(private val bagDishesDao: BagDishesDao) : ViewModel() {
    private val _liveData = MutableLiveData<List<BagDishesEntity>>()
    val liveData: MutableLiveData<List<BagDishesEntity>> get() = _liveData

    fun getData() {
        bagDishesDao.selectAll()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _liveData.postValue(it)
                }, {}
            )
    }
}