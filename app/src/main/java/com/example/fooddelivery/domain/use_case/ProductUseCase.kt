package com.example.fooddelivery.domain.use_case

import com.example.fooddelivery.data.remote.dto.toProductDescriptionModel
import com.example.fooddelivery.data.remote.dto.toProductMenuModel
import com.example.fooddelivery.data.repository.DataRepositoryImpl
import com.example.fooddelivery.domain.model.ProductDescriptionModel
import com.example.fooddelivery.domain.model.ProductInShopCartModel
import com.example.fooddelivery.domain.model.ProductMenuModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val dataRepositoryImpl: DataRepositoryImpl
) {

    private val productListDto = dataRepositoryImpl.getProductList()

    //По скольку в тегах нет тега "Скидка", вместо id тега тут Boolean
    //И проходиться использовать вот такую карусель с if`ами
    var list = listOf(false, false, false)

    fun getProductMenu(): List<ProductMenuModel> {
        var productMenu = productListDto.map {
            it.toProductMenuModel()
        }
        if (list[0]) {
            productMenu = productMenu.filter { it.tagIds.contains(2) }
        }
        if (list[1]) {
            productMenu = productMenu.filter { it.tagIds.contains(4) }
        }
        if (list[2]) {
            productMenu = productMenu.filter { it.priceOld != null }
        }
        return productMenu
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

    fun getProductListInShopCart(listId: Map<Int, Int>): MutableList<ProductInShopCartModel> {
        val productList = mutableListOf<ProductInShopCartModel>()
        listId.forEach { map ->
            val product = productListDto.firstOrNull { it.id == map.key }
            product?.let {
                productList.add(
                    ProductInShopCartModel(
                        id = product.id,
                        name = product.name,
                        priceCurrent = product.priceCurrent / 100,
                        priceOld = product.priceOld?.let { it / 100 },
                        image = product.image,
                        quantity = map.value
                    )
                )
            }
        }
        return productList
    }
}