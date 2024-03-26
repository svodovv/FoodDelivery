package com.example.fooddelivery.presentation.ui.ShoppingCart

data class ShoppingCartState(
    val price: Int = 0,
    val mapPriceIdTiPairPriceQuantity: Map<Int,Pair<Int, Int>> = emptyMap()
)
