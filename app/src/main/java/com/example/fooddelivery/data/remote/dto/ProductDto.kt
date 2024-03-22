package com.example.fooddelivery.data.remote.dto

import com.example.fooddelivery.domain.model.ProductMenuModel
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("carbohydrates_per_100_grams")
    val carbohydratesPer100Grams: Double,
    @SerializedName("category_id")
    val categoryId: Int,
    val description: String,
    @SerializedName("energy_per_100_grams")
    val energyPer100Grams: Double,
    @SerializedName("fats_per_100_grams")
    val fatsPer100Grams: Double,
    val id: Int,
    val image: String,
    val measure: Int,
    @SerializedName("measure_unit")
    val measureUnit: String,
    val name: String,
    @SerializedName("price_current")
    val priceCurrent: Int,
    @SerializedName("price_old")
    val priceOld: Int?,
    @SerializedName("proteins_per_100_grams")
    val proteinsPer100Grams: Double,
    @SerializedName("tag_ids")
    val tagIds: List<Int>
)

fun ProductDto.toProductMenuModel(): ProductMenuModel {
    val priceCurrent = priceCurrent / 100
    val priceOld = priceOld?.div(100)
    return ProductMenuModel(
        name = name,
        priceCurrent = priceCurrent,
        priceOld = priceOld,
        image = image,
        measure = measure,
        measureUnit = measureUnit
    )
}