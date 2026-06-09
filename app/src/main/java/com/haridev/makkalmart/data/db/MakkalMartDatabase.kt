package com.haridev.makkalmart.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haridev.makkalmart.data.db.ProductDao
import com.haridev.makkalmart.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class MakkalMartDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}

