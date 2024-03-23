package com.omgupsapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fooddelivery.presentation.ui.MenuScreen.components.MenuScreen


@Composable
fun NavHostComposable(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    val startDestination = Screen.MenuScreen.route

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(route = Screen.MenuScreen.route){
           MenuScreen(navController = navController, paddingValues = paddingValues)
        }
    }
}

sealed class Screen(val route: String) {
    object MenuScreen: Screen("menu_screen")
}
