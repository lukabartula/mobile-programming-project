package com.example.quickbasket.data.repositories

import com.example.quickbasket.data.daos.UsersDao
import com.example.quickbasket.data.models.Users

class UserRepository(private val usersDao: UsersDao) {
    suspend fun register(t: Users) = usersDao.register(t)

    suspend fun update(t: Users) = usersDao.update(t)

    suspend fun delete(t: Users) = usersDao.delete(t)

    suspend fun getUserByID(id: Int): Users = usersDao.getUserByID(id)

    suspend fun getUserByEmail(email: String): Users? = usersDao.getUserByEmail(email)

    suspend fun login(password: String, username: String): Users? = usersDao.login(password, username)



}