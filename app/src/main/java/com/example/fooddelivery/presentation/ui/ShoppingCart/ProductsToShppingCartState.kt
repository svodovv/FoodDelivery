package com.example.fooddelivery.presentation.ui.ShoppingCart

data class ProductsToShppingCartState(
    val price: Int = 0,
    val mapProductIdTiPairPriceQuantity: Map<Int,Pair<Int, Int>> = emptyMap()
)
