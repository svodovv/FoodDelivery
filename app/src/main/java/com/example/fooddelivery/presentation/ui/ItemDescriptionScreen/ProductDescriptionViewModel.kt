package com.example.fooddelivery.presentation.ui.ItemDescriptionScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDescriptionViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val _productInfo = mutableStateOf(ProductInfoState())
    val productInfo: State<ProductInfoState> = _productInfo

    fun getItemInfo(productInfo: Int) {
        viewModelScope.launch {
            val productInformation = productUseCase.getProductInfo(productId = productInfo)
            _productInfo.value = _productInfo.value.copy(productInfo = productInformation)
        }
    }
}
