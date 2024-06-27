package com.example.quickbasket.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.quickbasket.data.daos.ProductsDao
import com.example.quickbasket.data.models.Products
import com.example.quickbasket.data.models.toProduct
import kotlinx.coroutines.flow.flatMapConcat

class ProductRepository(private val productsDao: ProductsDao) {

    suspend fun insert(t: Products) = productsDao.insert(t)

    suspend fun update(t: Products) = productsDao.update(t)

    suspend fun delete(t: Products) = productsDao.delete(t)

    suspend fun getAllProducts(): List<Products> = productsDao.getAllProducts()

    suspend fun getProductById(id: Int): Products = productsDao.getProductById(id)


}