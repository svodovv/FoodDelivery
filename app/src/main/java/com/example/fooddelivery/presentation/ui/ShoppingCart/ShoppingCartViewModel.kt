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
    private val _shoppingCartState = MutableStateFlow(ShoppingCartState())
    val shoppingCartState = _shoppingCartState.asStateFlow()

    fun shoppingCartUpdate(price: Int, map:Map<Int,Pair<Int, Int>>){
        _shoppingCartState.update { shoppingCartState ->
            shoppingCartState.copy(price = price, mapPriceIdTiPairPriceQuantity = map)
        }
    }

    fun updateProductList(tripeProductIdPriceQuantity: Triple<Int,Int, Int>){

        _shoppingCartState.update {shoppingCartState ->
            val mapProductIdToQuantity = shoppingCartState.mapPriceIdTiPairPriceQuantity.toMutableMap()
            mapProductIdToQuantity[tripeProductIdPriceQuantity.first] =
                Pair(tripeProductIdPriceQuantity.second, tripeProductIdPriceQuantity.third)
            shoppingCartState.copy(mapPriceIdTiPairPriceQuantity = mapProductIdToQuantity)
        }

        _shoppingCartState.update {shoppingCartState ->
            var price = 0
            shoppingCartState.mapPriceIdTiPairPriceQuantity.forEach{
                price += it.value.first * it.value.second
            }
            shoppingCartState.copy(price = price)
        }
    }
}