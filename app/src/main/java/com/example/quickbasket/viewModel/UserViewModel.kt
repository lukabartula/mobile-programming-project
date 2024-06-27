package com.example.quickbasket.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickbasket.data.UserPreferences
import com.example.quickbasket.data.repositories.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel (private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModel(){
    var userUiState by mutableStateOf(UsersUiState())
        private set

    init {
        viewModelScope.launch {
            val user = userRepository.getUserByID(userPreferences.getLoggedInUserId())
            if (user != null) {
                userUiState = user.toUserUiState()
            } else {
                userUiState = UsersUiState()
            }
        }
    }

    fun updateUiState(userDetails: UsersDetails) {
        userUiState = userUiState.copy(usersDetails = userDetails)
    }


    private fun validateRegisterInput(): Boolean {
        val details = userUiState.usersDetails
        return details.name.isNotBlank() && details.email.isNotBlank() && details.password.isNotBlank()
    }

    fun logout() {
        userPreferences.clear()
        userUiState = UsersUiState()
    }

    fun isLoggedIn(): Boolean {
        return userPreferences.isUserLoggedIn()
    }
    fun getLoggedInUserId(): Int {
        return userPreferences.getLoggedInUserId()
    }

    suspend fun registerUser(onResult: (Boolean, String?) -> Unit) {
        if (validateRegisterInput()) {
            val existingUser = userRepository.getUserByEmail(userUiState.usersDetails.email)

            if (existingUser != null) {
                onResult(false, "Email already registered")
            } else {
                userRepository.register(userUiState.usersDetails.toUsers())
                onResult(true, null)
            }
        } else {
            onResult(false, "Invalid input")
        }
    }

    fun updateUserDetails(newName: String, newEmail: String, newPassword: String) {
        val updatedDetails = userUiState.usersDetails.copy(name = newName, email = newEmail, password = newPassword)
        userUiState = userUiState.copy(usersDetails = updatedDetails)
        viewModelScope.launch {
            userRepository.update(userUiState.usersDetails.toUsers())
        }
    }

    fun updateEmail(newEmail: String) {
        val updatedDetails = userUiState.usersDetails.copy(email = newEmail)
        userUiState = userUiState.copy(usersDetails = updatedDetails)
    }

    fun updatePassword(newPassword: String) {
        val updatedDetails = userUiState.usersDetails.copy(password = newPassword)
        userUiState = userUiState.copy(usersDetails = updatedDetails)
    }

    private fun validateLoginInput(): Boolean {
        return userUiState.usersDetails.email.isNotBlank() && userUiState.usersDetails.password.isNotBlank()
    }

    suspend fun signInUser(onResult: (Boolean, String?) -> Unit) {
        if (validateLoginInput()) {
            val user = userRepository.login(userUiState.usersDetails.password, userUiState.usersDetails.email)
            if (user != null) {
                userPreferences.setUserLoggedIn(true)
                userPreferences.setLoggedInUserId(user.id)
                userUiState = user.toUserUiState()
                onResult(true, null)
            } else {
                onResult(false, "Invalid username or password")
                Log.d("UserViewModel", "Invalid username or password")
            }
        } else {
            onResult(false, "Please enter both username and password")
        }
    }
    suspend fun deleteUser() {
        userRepository.delete(userUiState.usersDetails.toUsers())
        userUiState = UsersUiState()
        userPreferences.clear()
    }


}