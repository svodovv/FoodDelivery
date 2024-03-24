package com.example.fooddelivery.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.omgupsapp.presentation.navigation.NavHostComposable

@Composable
fun App() {
    val navController = rememberNavController()

    NavHostComposable(navController )

}