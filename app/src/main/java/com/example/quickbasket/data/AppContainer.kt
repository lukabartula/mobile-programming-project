package com.example.quickbasket.data

import android.content.Context
import com.example.quickbasket.data.repositories.FavouriteRepository
import com.example.quickbasket.data.repositories.ProductRepository
import com.example.quickbasket.data.repositories.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val productRepository: ProductRepository
    val favouriteRepository: FavouriteRepository
}

class AppDataContainer(private val context: Context): AppContainer {


    override val userRepository: UserRepository by lazy {
        UserRepository(UserDatabase.getDatabase(context).userDao())
    }


    override val productRepository: ProductRepository by lazy {
        ProductRepository(UserDatabase.getDatabase(context).productDao())
    }


    override val favouriteRepository: FavouriteRepository by lazy {
        FavouriteRepository(UserDatabase.getDatabase(context).favouriteDao())
    }
}