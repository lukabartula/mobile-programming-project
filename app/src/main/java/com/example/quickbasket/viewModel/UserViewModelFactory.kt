package com.example.quickbasket.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.quickbasket.data.daos.UsersDao
import com.example.quickbasket.data.UserPreferences
import com.example.quickbasket.data.repositories.UserRepository
import com.example.quickbasket.viewModel.UserViewModel

class UserViewModelFactory(private val userDao: UsersDao, private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(UserRepository(userDao), userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}