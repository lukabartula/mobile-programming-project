package com.example.quickbasket.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quickbasket.data.models.Favourites
import com.example.quickbasket.data.models.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Products)

    @Update
    suspend fun update(product: Products)

    @Delete
    suspend fun delete(product: Products)

    @Query("SELECT * FROM Products WHERE id = :id")
    suspend fun getProductById(id: Int): Products

    @Query("SELECT * FROM Products")
    suspend fun getAllProducts(): List<Products>

    @Query("SELECT * FROM Favourites WHERE user_id = :userId")
    suspend fun getUserFavouriteProducts(userId: Int): List<Favourites>
}
