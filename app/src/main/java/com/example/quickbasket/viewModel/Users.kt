package com.example.quickbasket.viewModel

import com.example.quickbasket.data.models.Users

data class UsersDetails(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = ""
)

data class UsersUiState(
    val usersDetails: UsersDetails = UsersDetails(),
    val isEntryValid: Boolean = false,
    val registrationSuccess: Boolean = false,
    val errorMessage: String? = null
)

fun UsersDetails.toUsers(): Users = Users(
    id = id,
    name = name,
    email = email,
    password = password
)

fun Users.toUsersDetails() = UsersDetails(
    id = id,
    name = name,
    email = email,
    password = password
)

fun Users.toUserUiState(isEntryValid: Boolean = false): UsersUiState = UsersUiState(
    usersDetails = this.toUsersDetails(),
    isEntryValid = isEntryValid
)

