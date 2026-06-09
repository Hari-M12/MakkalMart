package com.haridev.makkalmart.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.haridev.makkalmart.MakkalMartApp
import com.haridev.makkalmart.data.model.Product
import com.haridev.makkalmart.data.repository.MakkalMartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MakkalMartViewModel(application: Application): AndroidViewModel(application) {
    val app = application as MakkalMartApp
    private val repository = MakkalMartRepository(app.database)
    private val mutableProduct = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = mutableProduct

    fun loadProduct(){
        viewModelScope.launch {
            mutableProduct.value= repository.getAllProduct()
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun getProductById(id: Int): Product? {
        return mutableProduct.value.find { it.id == id }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product)
            loadProduct()
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
            loadProduct()
        }
    }
}