package com.example.fooddelivery.presentation.ui.SearchProduct

import com.example.fooddelivery.domain.model.ProductMenuModel

data class SearchProductState(
    val productList: List<ProductMenuModel> = emptyList(),
    val inputText: String = ""
)