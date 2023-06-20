package com.example.repository.data.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bag_dishes")
data class BagDishesEntity(
    @field:PrimaryKey
    @NonNull
    @field:ColumnInfo(name = "id") var id: Int,
    @field:ColumnInfo(name = "name") var name: String,
    @field:ColumnInfo(name = "price") var price: Int,
    @field:ColumnInfo(name = "weight") var weight: Int,
    @field:ColumnInfo(name = "image_url") var image_url: String,
    @field:ColumnInfo(name = "user_id") var user_id: Int,
    @field:ColumnInfo(name = "cnt") var cnt: Int
)
