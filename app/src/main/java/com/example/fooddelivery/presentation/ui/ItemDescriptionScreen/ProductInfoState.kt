package com.example.fooddelivery.presentation.ui.ItemDescriptionScreen

import com.example.fooddelivery.domain.model.ProductDescriptionModel

data class ProductInfoState(
    val productInfo: ProductDescriptionModel = ProductDescriptionModel(
        0,
        "",
        "",
        "",
        0,
        "",
        0.0,
        0.0,
        0.0,
        0.0
    )
)
