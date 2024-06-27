package com.example.quickbasket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.quickbasket.data.UserDatabase
import com.example.quickbasket.data.UserPreferences
import com.example.quickbasket.ui.UserNavHost
import com.example.quickbasket.ui.theme.QuickbasketTheme
import com.example.quickbasket.viewModel.FavouritesViewModel
import com.example.quickbasket.viewModel.FavouritesViewModelFactory
import com.example.quickbasket.viewModel.ProductViewModel
import com.example.quickbasket.viewModel.UserViewModel
import com.example.quickbasket.viewModel.ProductViewModelFactory
import com.example.quickbasket.viewModel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDao = UserDatabase.getDatabase(this).userDao()
        val productsDao = UserDatabase.getDatabase(this).productDao()
        val favouritesDao = UserDatabase.getDatabase(this).favouriteDao()
        val userPreferences = UserPreferences(this)
        val userViewModel = ViewModelProvider(this, UserViewModelFactory(userDao, userPreferences))[UserViewModel::class.java]
        val productViewModel = ViewModelProvider(this, ProductViewModelFactory(productsDao))[ProductViewModel::class.java]
        val favouritesViewModel = ViewModelProvider(this, FavouritesViewModelFactory(favouritesDao, userPreferences))[FavouritesViewModel::class.java]
        setContent {
            QuickbasketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserNavHost(navController = rememberNavController(),userViewModel = userViewModel, productViewModel = productViewModel, favouritesViewModel= favouritesViewModel)
                }
            }
        }
    }
}