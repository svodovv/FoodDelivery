package com.example.fooddelivery.data.remote.dto

import com.example.fooddelivery.domain.model.CategoriesModel

data class CategoriesDto(
    val id: Int,
    val name: String
)
fun CategoriesDto.toCategoriesListModel(): CategoriesModel {
    return CategoriesModel(
        id = id,
        name = name
    )
}