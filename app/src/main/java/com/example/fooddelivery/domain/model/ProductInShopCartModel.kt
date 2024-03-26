package com.example.fooddelivery.domain.model

data class ProductInShopCartModel(
    val id: Int,
    val name: String,
    val priceCurrent: Int,
    val priceOld: Int?,
    val image: String,
    val quantity: Int,
)
