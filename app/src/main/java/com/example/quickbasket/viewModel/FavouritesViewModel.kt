package com.example.quickbasket.viewModel

import android.media.midi.MidiDeviceInfo.PortInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickbasket.data.UserPreferences
import com.example.quickbasket.data.models.Favourites
import com.example.quickbasket.data.models.Products
import com.example.quickbasket.data.repositories.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(private val repository: FavouriteRepository, private val userPreferences: UserPreferences) : ViewModel() {

    private val _favourites = MutableStateFlow<List<Products>>(emptyList())
    val favourites: MutableStateFlow<List<Products>> = _favourites

    init {
        viewModelScope.launch{
            getAllFavouritesForUser(userPreferences.getLoggedInUserId()).collect{
                _favourites.value = it
            }
        }
    }

    fun getAllFavouritesForUser(userId: Int): Flow<List<Products>> {
        return repository.getAllFavouritesForUser(userId)
    }

    fun clearFavourites() {
        _favourites.value = emptyList()
    }


    suspend fun addToFavourites(userId: Int, productId: Int) {
        repository.insert(Favourites(userId = userId, productId = productId))
    }
}