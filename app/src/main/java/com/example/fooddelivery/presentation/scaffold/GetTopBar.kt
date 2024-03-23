package com.omgupsapp.presentation.scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun GetTopBar(
    navController: NavController, route: String, selectedTitle: String
) {
    if (route == "") {
        //Пустой @Composable
    } else {
        TopAppBarComposable(
            navController = navController,
        )
    }
}
