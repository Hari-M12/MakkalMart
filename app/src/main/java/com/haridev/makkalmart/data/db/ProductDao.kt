package com.haridev.makkalmart.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.haridev.makkalmart.data.model.Product

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts():List<Product>

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

}