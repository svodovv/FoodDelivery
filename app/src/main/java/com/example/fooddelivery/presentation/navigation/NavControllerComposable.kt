package com.omgupsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fooddelivery.presentation.ui.ItemDescriptionScreen.components.ProductDescriptionScreen
import com.example.fooddelivery.presentation.ui.MenuScreen.components.MenuScreen
import com.example.fooddelivery.presentation.ui.SearchProduct.components.SearchProductScreen
import com.example.fooddelivery.presentation.ui.ShoppingCart.ShoppingCartViewModel
import com.example.fooddelivery.presentation.ui.ShoppingCart.components.ShoppingCartScreen


@Composable
fun NavHostComposable(
    navController: NavHostController
) {


    NavHost(
        navController = navController, startDestination = Screen.NavGroup.route
    ) {
        navigation(startDestination = Screen.MenuScreen.route, route = Screen.NavGroup.route) {

            composable(route = Screen.MenuScreen.route) { navBackStackEntry ->
                val viewModel =
                    navBackStackEntry.sharedViewModel<ShoppingCartViewModel>(navController = navController)

                MenuScreen(
                    navController = navController, shoppingCartViewModel = viewModel
                )
            }
            composable(route = Screen.MenuScreen.route + "/{itemId}") { navBackStackEntry ->
                val itemId = navBackStackEntry.arguments?.getString("itemId")

                val viewModel =
                    navBackStackEntry.sharedViewModel<ShoppingCartViewModel>(navController = navController)


                itemId?.let {
                    ProductDescriptionScreen(
                        id = itemId.toInt(),
                        navController = navController,
                        shoppingCartViewModel = viewModel
                    )
                }
            }
            composable(route = Screen.SearchProductScreen.route) { navBackStackEntry ->
                val viewModel =
                    navBackStackEntry.sharedViewModel<ShoppingCartViewModel>(navController = navController)

                SearchProductScreen(
                    navController = navController, shoppingCartViewModel = viewModel
                )
            }
            composable(route = Screen.ShopCartScreen.route) { navBackStackEntry ->
                val viewModel =
                    navBackStackEntry.sharedViewModel<ShoppingCartViewModel>(navController = navController)
                ShoppingCartScreen(
                    navController = navController,
                    shoppingCartViewModel = viewModel
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

sealed class Screen(val route: String) {
    object MenuScreen : Screen("menu_screen")
    object SearchProductScreen : Screen("search_product_screen")
    object ShopCartScreen : Screen("shop_cart_screen")
    object NavGroup : Screen("nav_group")
}
