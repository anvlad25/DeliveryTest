package com.example.repository.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [BagDishesEntity::class], version = 1, exportSchema = true)
abstract class BagDatabase: RoomDatabase() {
    abstract fun bagDishesDao(): BagDishesDao
}