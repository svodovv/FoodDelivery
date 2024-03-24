package com.omgupsapp.presentation.scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.fooddelivery.presentation.scaffold.SearchTopAppBar
import com.omgupsapp.presentation.navigation.Screen

@Composable
fun GetTopBar(
    navController: NavController, route: String, selectedTitle: String
) {
    when (route) {
        Screen.MenuScreen.route -> {
            TopAppBarComposable(
                navController = navController,
            )
        }
        Screen.SearchProductScreen.route -> {
            SearchTopAppBar(navController = navController)
        }
        else -> {
            //
        }
    }
}
