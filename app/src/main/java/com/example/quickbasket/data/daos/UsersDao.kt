package com.example.quickbasket.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quickbasket.data.models.Users

@Dao
interface UsersDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun register(user: Users)

    @Update
    suspend fun update(user: Users)

    @Delete
    suspend fun delete(user: Users)

    @Query("SELECT * FROM Users WHERE id = :id")
    suspend fun getUserByID(id: Int): Users

    @Query("SELECT * FROM Users WHERE email = :email")
    suspend fun getUserByEmail(email: String): Users?

    @Query("SELECT * FROM Users WHERE password = :password AND email = :email")
    suspend fun login(password: String, email: String): Users?

}