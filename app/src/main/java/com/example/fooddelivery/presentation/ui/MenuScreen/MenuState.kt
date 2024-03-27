package com.example.fooddelivery.presentation.ui.MenuScreen

import com.example.fooddelivery.domain.model.CategoriesModel
import com.example.fooddelivery.domain.model.ProductMenuModel

data class MenuState(
    val productList: List<ProductMenuModel> = emptyList(),
    val categoriesList: List<CategoriesModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: String = ""
)
