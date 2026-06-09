package com.haridev.makkalmart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val ratingRate: Double,
    val ratingCount: Int
)