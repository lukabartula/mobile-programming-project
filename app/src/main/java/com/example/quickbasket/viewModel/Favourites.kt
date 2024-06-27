package com.example.quickbasket.viewModel

import com.example.quickbasket.data.models.Favourites

data class FavouritesDetails(
    val id: Int = 0,
    val userId: Int = 0,
    val productId: Int = 0
)

data class FavouritesUiState(
    val favouritesList: List<FavouritesDetails> = emptyList(),
    val favouritesDetails: FavouritesDetails = FavouritesDetails(),
    val isEntryValid: Boolean = false,
)
fun FavouritesDetails.toFavourites(): Favourites = Favourites(
    id = id,
    userId = userId,
    productId = productId
)

fun Favourites.toFavouritesDetails() = FavouritesDetails(
    id = id,
    userId = userId,
    productId = productId
)

fun Favourites.toFavouritesUiState(isEntryValid: Boolean = false): FavouritesUiState = FavouritesUiState(
    favouritesDetails = this.toFavouritesDetails(),
    favouritesList = listOf(this.toFavouritesDetails()),
    isEntryValid = isEntryValid
)
