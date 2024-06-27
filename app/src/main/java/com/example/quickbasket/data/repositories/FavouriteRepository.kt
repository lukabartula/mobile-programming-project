package com.example.quickbasket.data.repositories

import com.example.quickbasket.data.daos.FavouritesDao
import com.example.quickbasket.data.models.Favourites
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouriteRepository(private val favouritesDao: FavouritesDao){
    suspend fun insert(t: Favourites) = favouritesDao.insert(t)

    suspend fun update(t: Favourites) = favouritesDao.update(t)

    suspend fun delete(t: Favourites) = favouritesDao.delete(t)

    fun getAllFavouritesForUser(id: Int) = favouritesDao.getFavouritesByUserID(id)

    suspend fun deleteByUserIdAndProductId(userId: Int, productId: Int) {
        favouritesDao.deleteByUserIdAndProductId(userId, productId)
    }
}