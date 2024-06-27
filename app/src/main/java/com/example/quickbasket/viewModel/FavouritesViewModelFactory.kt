package com.example.quickbasket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.quickbasket.data.UserPreferences
import com.example.quickbasket.data.daos.FavouritesDao
import com.example.quickbasket.data.daos.ProductsDao
import com.example.quickbasket.data.repositories.FavouriteRepository
import com.example.quickbasket.data.repositories.ProductRepository

class FavouritesViewModelFactory(private val favouritesDao: FavouritesDao, private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouritesViewModel(FavouriteRepository(favouritesDao), userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}