package com.example.fooddelivery.domain.model

data class ProductDescriptionModel(
    val priceCurrent: Int,
    val image: String,
    val name: String,
    val description: String,
    val measure: Int,
    val measureUnit: String,
    val energyPer100Grams: Double,
    val carbohydratesPer100Grams: Double,
    val proteinsPer100Grams: Double,
    val fatsPer100Grams: Double
)
