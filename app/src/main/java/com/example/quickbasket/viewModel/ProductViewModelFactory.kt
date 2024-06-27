package com.example.quickbasket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.quickbasket.data.daos.ProductsDao
import com.example.quickbasket.data.repositories.ProductRepository

class ProductViewModelFactory(private val productsDao: ProductsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(ProductRepository(productsDao = productsDao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}