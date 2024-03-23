package com.omgupsapp.presentation.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun ScaffoldComposable(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedTitle = remember { mutableStateOf("") }

    Scaffold(topBar = {
        GetTopBar(
            navController = navController,
            route = navBackStackEntry?.destination?.route.toString(),
            selectedTitle = selectedTitle.value
        )
    }) { paddingValues ->
        content(paddingValues)
    }
}
