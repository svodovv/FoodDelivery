package com.example.fooddelivery.presentation.ui.MenuScreen

import com.example.fooddelivery.domain.model.ProductMenuModel

data class MenuState(
    val productList: List<ProductMenuModel> = emptyList(),
)
