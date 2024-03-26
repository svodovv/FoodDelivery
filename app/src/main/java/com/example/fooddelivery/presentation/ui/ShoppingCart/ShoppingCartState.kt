package com.example.fooddelivery.presentation.ui.ShoppingCart

import com.example.fooddelivery.domain.model.ProductInShopCartModel

data class ShoppingCartState(
    val productList: List<ProductInShopCartModel> = emptyList()
)
