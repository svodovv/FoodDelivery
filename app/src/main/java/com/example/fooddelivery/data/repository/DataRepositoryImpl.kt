package com.example.fooddelivery.data.repository

import android.content.Context
import com.example.fooddelivery.R
import com.example.fooddelivery.data.remote.dto.CategoriesDto
import com.example.fooddelivery.data.remote.dto.ProductDto
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val productListType: Type = object : TypeToken<List<ProductDto>>() {}.type
    private val categoriesListType: Type = object : TypeToken<List<CategoriesDto>>() {}.type

    fun getProductList() =  JsonDataProvider(context)
        .loadJsonDataList<ProductDto>(
            resId = R.raw.r_products, productListType
        )

    fun getCategoriesList() = JsonDataProvider(context)
        .loadJsonDataList<CategoriesDto>(
            R.raw.r_categories, categoriesListType
        )
}