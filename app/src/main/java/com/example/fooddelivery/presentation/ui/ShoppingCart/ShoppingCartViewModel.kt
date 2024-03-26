package com.example.fooddelivery.presentation.ui.ShoppingCart

import androidx.lifecycle.ViewModel
import com.example.fooddelivery.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel(){
    private val _productsToShoppingCartState = MutableStateFlow(ProductsToShppingCartState())
    val productsToShoppingCartState = _productsToShoppingCartState.asStateFlow()

    private val _shoppingCartState = MutableStateFlow(ShoppingCartState())
    val shoppingCartState = _shoppingCartState.asStateFlow()

    fun getSelectedProduct(keys: Map<Int, Int>){
        val productList = productUseCase.getProductListInShopCart(keys)
        _shoppingCartState.update {
            it.copy(productList = productList)
        }
    }

    fun updateProductList(tripeProductIdPriceQuantity: Triple<Int,Int, Int>){

        _productsToShoppingCartState.update { shoppingCartState ->
            val mapProductIdToQuantity = shoppingCartState.mapProductIdTiPairPriceQuantity.toMutableMap()
            mapProductIdToQuantity[tripeProductIdPriceQuantity.first] =
                Pair(tripeProductIdPriceQuantity.second, tripeProductIdPriceQuantity.third)

            val filterMap = mapProductIdToQuantity.filter { it.value.second != 0 }

            shoppingCartState.copy(mapProductIdTiPairPriceQuantity = filterMap)
        }

        _productsToShoppingCartState.update { shoppingCartState ->
            var price = 0
            shoppingCartState.mapProductIdTiPairPriceQuantity.forEach{
                price += it.value.first * it.value.second
            }
            shoppingCartState.copy(price = price)
        }
    }
}