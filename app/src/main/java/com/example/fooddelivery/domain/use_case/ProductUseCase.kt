package com.example.fooddelivery.domain.use_case

import com.example.fooddelivery.data.remote.dto.toProductMenuModel
import com.example.fooddelivery.data.repository.DataRepositoryImpl
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val dataRepositoryImpl: DataRepositoryImpl
) {
    fun getProductList() = dataRepositoryImpl.getProductList()
        .map {
            it.toProductMenuModel()
        }

}