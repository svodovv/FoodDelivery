package com.example.fooddelivery.domain.model

import com.google.gson.annotations.SerializedName

data class ProductMenuModel(
    val name: String,
    val priceCurrent: Int,
    val priceOld: Int?,
    val image: String,
    val measure: Int,
    val measureUnit: String,
)
