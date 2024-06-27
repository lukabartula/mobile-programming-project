package com.example.quickbasket.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quickbasket.ui.screen.CatalogueScreen
import com.example.quickbasket.ui.screen.EditProfileScreen
import com.example.quickbasket.ui.screen.ProfileScreen
import com.example.quickbasket.ui.screen.SigninScreen
import com.example.quickbasket.ui.screen.SignupScreen
import com.example.quickbasket.ui.screen.WelcomeScreen
import com.example.quickbasket.viewModel.ProductViewModel
import com.example.quickbasket.viewModel.UserViewModel
import com.example.quickbasket.ui.screen.SingleProductScreen
import com.example.quickbasket.viewModel.FavouritesViewModel


sealed class Screen(val route: String){
    object WelcomeDestination: Screen("welcome")
    object SigninDestination: Screen("signin")
    object SignupDestination: Screen("signup")
    object ProfileDestination: Screen("profile")
    object EditProfileDestination: Screen("editProfile")
    object CatalogueDestination: Screen("catalogue")
    object SingleProductDestination: Screen("singleProduct")
}

@Composable
fun UserNavHost(
    navController: NavHostController,
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel,
    favouritesViewModel: FavouritesViewModel
){
    NavHost(navController = navController, startDestination = Screen.WelcomeDestination.route){
        composable(route = Screen.SigninDestination.route){
            SigninScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }
        composable(route = Screen.SignupDestination.route){
            SignupScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }

        composable(route = Screen.WelcomeDestination.route) {
            WelcomeScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(route = Screen.CatalogueDestination.route) {
            CatalogueScreen(
                navController = navController,
                userViewModel = userViewModel,
                productViewModel = productViewModel
            )
        }
        composable("singleProduct/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            SingleProductScreen(navController = navController, productViewModel = productViewModel, userViewModel = userViewModel, favouritesViewModel = favouritesViewModel,productId = productId)
        }

        composable(route = "editProfile") {
            EditProfileScreen(navController = navController, userViewModel = userViewModel)
        }
        composable(route = Screen.ProfileDestination.route) {
            ProfileScreen(
                navController = navController,
                userViewModel = userViewModel,
                favouritesViewModel = favouritesViewModel
            )
        }


    }
}