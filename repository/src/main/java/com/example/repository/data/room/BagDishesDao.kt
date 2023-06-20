package com.example.repository.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Observable

@Dao
interface BagDishesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDish(dish: BagDishesEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllDish(dishes: List<BagDishesEntity>)

    @Query("SELECT * FROM bag_dishes")
    fun selectAll(): Observable<List<BagDishesEntity>>

    @Query("SELECT cnt FROM bag_dishes WHERE user_id = :userId and id = :dishId")
    fun selectCntDish(userId: Int, dishId: Int): Int

    @Query("SELECT * FROM bag_dishes WHERE user_id = :userId and id = :dishId")
    fun selectDish(userId: Int, dishId: Int): List<BagDishesEntity>

    @Query("SELECT sum(price * cnt) as total_price FROM bag_dishes")
    fun selectSumPay(): Int

    @Query("UPDATE bag_dishes SET cnt = :cnt WHERE user_id = :userId and id = :dishId")
    fun updateCntDish(userId: Int, dishId: Int, cnt: Int)

    @Update
    fun updateDish(bagDishesEntity: BagDishesEntity)

    @Query("DELETE FROM bag_dishes WHERE user_id = :userId and id = :dishId")
    fun deleteDish(userId: Int, dishId: Int)

    @Query("DELETE FROM bag_dishes")
    fun deleteAll()
}