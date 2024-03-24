package com.example.fooddelivery.presentation.ui.SearchProduct

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val _searchProductState = mutableStateOf(SearchProductState())
    val searchProductState: State<SearchProductState> = _searchProductState


    private fun searchProduct(productName: String) {
        viewModelScope.launch {
            val productList = productUseCase.searchProduct(productName)
            _searchProductState.value = _searchProductState.value.copy(productList = productList)
        }
    }

    fun onChangeInputSearch(inputText: String) {
        _searchProductState.value = _searchProductState.value.copy(inputText = inputText)

        Log.e("inputText", searchProductState.value.inputText)
        searchProduct(searchProductState.value.inputText)
        Log.e("productList", searchProductState.value.productList.toString())

    }

    fun deleteInputText() {
        _searchProductState.value = _searchProductState.value.copy(inputText = "")
    }

}