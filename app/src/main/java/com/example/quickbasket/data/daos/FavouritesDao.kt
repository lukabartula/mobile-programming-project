package com.example.quickbasket.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

import com.example.quickbasket.data.models.Favourites
import com.example.quickbasket.data.models.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favourite: Favourites)

    @Update
    suspend fun update(favourite: Favourites)

    @Delete
    suspend fun delete(favourite: Favourites)

    @Query("SELECT * FROM Favourites WHERE product_id = :productId")
    suspend fun getFavoriteById(productId: Int): Favourites?


    @Query("DELETE FROM Favourites WHERE user_id = :userId AND product_id = :productId")
    suspend fun deleteByUserIdAndProductId(userId: Int, productId: Int)

    @Transaction
    @Query("""
        SELECT Products.* FROM Favourites
        INNER JOIN Products ON Favourites.product_id = Products.id
        WHERE Favourites.user_id = :userId
    """)
    fun getFavouritesByUserID(userId: Int): Flow<List<Products>>
}