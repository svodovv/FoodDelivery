package com.example.fooddelivery.domain.use_case

import com.example.fooddelivery.data.remote.dto.toProductDescriptionModel
import com.example.fooddelivery.data.remote.dto.toProductMenuModel
import com.example.fooddelivery.data.repository.DataRepositoryImpl
import com.example.fooddelivery.domain.model.ProductDescriptionModel
import com.example.fooddelivery.domain.model.ProductMenuModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val dataRepositoryImpl: DataRepositoryImpl
) {

    private val productListDto = dataRepositoryImpl.getProductList()

    fun getProductMenu() = productListDto.map {
        it.toProductMenuModel()
    }

    fun getProductInfo(productId: Int): ProductDescriptionModel =
        productListDto.first { it.id == productId }.toProductDescriptionModel()

    suspend fun searchProduct(productName: String): List<ProductMenuModel> {
        val productList = CoroutineScope(Dispatchers.Main).async {
            return@async productListDto.map {
                it.toProductMenuModel()
            }.filter { it.name.lowercase().contains(productName.lowercase()) }
        }
        return productList.await()
    }
}