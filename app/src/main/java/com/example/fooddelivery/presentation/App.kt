package com.example.fooddelivery.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.omgupsapp.presentation.navigation.NavHostComposable
import com.omgupsapp.presentation.scaffold.ScaffoldComposable

@Composable
fun App() {
    val navController = rememberNavController()

    ScaffoldComposable(navController = navController) { paddingValues ->
        NavHostComposable(navController, paddingValues)
    }
}