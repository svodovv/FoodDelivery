package com.example.fooddelivery.data.remote.Retrofit

import com.example.fooddelivery.data.remote.dto.CategoriesDto
import com.example.fooddelivery.data.remote.dto.ProductDto
import com.example.fooddelivery.data.remote.dto.TagsDto
import retrofit2.Response
import retrofit2.http.GET

interface FoodDeliveryApi {

    @GET("productList")
    suspend fun getProductList(): List<ProductDto>

    @GET("tags")
    suspend fun getTags(): List<TagsDto>


    //Если тут надо получить просто JSON то, вероятнее всего надо было
    //Использовать ScalarsConverterFactory
    @GET("splash_screen_anim")
    suspend fun splashScreenAnim(): Response<String>

    @GET("categories")
    suspend fun getCategories(): List<CategoriesDto>

}