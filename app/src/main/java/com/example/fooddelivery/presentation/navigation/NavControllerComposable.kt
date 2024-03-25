package com.omgupsapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.presentation.ui.ItemDescriptionScreen.components.ProductDescriptionScreen
import com.example.fooddelivery.presentation.ui.MenuScreen.components.MenuScreen
import com.example.fooddelivery.presentation.ui.SearchProduct.components.SearchProductScreen


@Composable
fun NavHostComposable(
    navController: NavHostController
) {

    val startDestination = Screen.MenuScreen.route

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(route = Screen.MenuScreen.route){
           MenuScreen(navController = navController)
        }
        composable(route = Screen.MenuScreen.route + "/{itemId}"){ navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString("itemId")
            itemId?.let {
                ProductDescriptionScreen(id = itemId.toInt(), navController = navController)
            }
        }
        composable(route = Screen.SearchProductScreen.route){
            SearchProductScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object MenuScreen: Screen("menu_screen")
    object SearchProductScreen: Screen("search_product_screen")
}
