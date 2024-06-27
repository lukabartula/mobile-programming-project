package com.example.quickbasket.viewModel

import androidx.lifecycle.ViewModel
import com.example.quickbasket.R
import com.example.quickbasket.data.models.Products
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.quickbasket.data.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    // Expose the products as LiveData
    private val _products = MutableStateFlow<List<Products>>(emptyList())
    val products: StateFlow<List<Products>> = _products.asStateFlow()

    init {
        viewModelScope.launch {
            if (productRepository.getAllProducts().isEmpty()) {
                insertProducts(productRepository)
            }
            _products.value = getAllProducts(productRepository)
        }

    }

    private suspend fun getAllProducts(productRepository: ProductRepository): List<Products> {
        return productRepository.getAllProducts()
    }

    suspend fun getProductById(productId: Int): Products {
        return productRepository.getProductById(productId)
    }

    private suspend fun insertProducts(productRepository: ProductRepository) {
        val product1 = Products(
            id = 1,
            name = "Bananas",
            description = "This is a bunch of bananas",
            price = 2,
            imageResId = R.drawable.bananas // replace with your actual resource id
        )

        val product2 = Products(
            id = 2,
            name = "Avocado",
            description = "This is an avocado",
            price = 5,
            imageResId = R.drawable.avocado // replace with your actual resource id
        )

        val product3 = Products(
            id = 3,
            name = "Butter",
            description = "This is butter",
            price = 4,
            imageResId = R.drawable.butter // replace with your actual resource id
        )

        val product4 = Products(
            id = 4,
            name = "Chips",
            description = "This is a packet of chips",
            price = 2,
            imageResId = R.drawable.chips // replace with your actual resource id
        )

        val product5 = Products(
            id = 5,
            name = "Chilly Pepper",
            description = "This is a chilly pepper",
            price = 4,
            imageResId = R.drawable.ljutapapricica // replace with your actual resource id
        )

        val product6 = Products(
            id = 6,
            name = "Pepsi",
            description = "This is a pepsi bottle",
            price = 1,
            imageResId = R.drawable.pepsi // replace with your actual resource id
        )

        val product7 = Products(
            id = 7,
            name = "Pizza",
            description = "This is a pizza",
            price = 3,
            imageResId = R.drawable.pizza // replace with your actual resource id
        )

        val product8 = Products(
            id = 8,
            name = "Pork",
            description = "This is pork meat",
            price = 12,
            imageResId = R.drawable.pork // replace with your actual resource id
        )

        val product9 = Products(
            id = 9,
            name = "Potatoes",
            description = "This is a bag of potatoes",
            price = 5,
            imageResId = R.drawable.potatoes // replace with your actual resource id
        )

        val product10 = Products(
            id = 10,
            name = "Rice",
            description = "This is a bag of rice",
            price = 2,
            imageResId = R.drawable.rice // replace with your actual resource id
        )

        val product11 = Products(
            id = 11,
            name = "Sugar",
            description = "This is a bag of sugar",
            price = 2,
            imageResId = R.drawable.sugar // replace with your actual resource id
        )

        // Insert the products into the database
        viewModelScope.launch {
            productRepository.insert(product1)
            productRepository.insert(product2)
            productRepository.insert(product3)
            productRepository.insert(product4)
            productRepository.insert(product5)
            productRepository.insert(product6)
            productRepository.insert(product7)
            productRepository.insert(product8)
            productRepository.insert(product9)
            productRepository.insert(product10)
            productRepository.insert(product11)
        }
    }
}