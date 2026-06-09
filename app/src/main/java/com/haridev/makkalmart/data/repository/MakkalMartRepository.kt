package com.haridev.makkalmart.data.repository

import com.haridev.makkalmart.data.db.MakkalMartDatabase
import com.haridev.makkalmart.data.model.Product


class MakkalMartRepository(private val db: MakkalMartDatabase) {
    suspend fun getAllProduct():List<Product> = db.productDao().getAllProducts()
    suspend fun insertProduct(product: Product) {
        db.productDao().insertProduct(product)
    }
    suspend fun updateProduct(product: Product) {
        db.productDao().updateProduct(product)
    }
    suspend fun deleteProduct(product: Product) {
        db.productDao().deleteProduct(product)
    }



}