package com.example.fooddelivery.presentation.ui.MenuScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.common.Resource
import com.example.fooddelivery.domain.use_case.CategoriesUseCase
import com.example.fooddelivery.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val productUserCase: ProductUseCase,
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {
    private val _menuState = mutableStateOf(MenuState())
    val menuState: State<MenuState> = _menuState


    init {
        Log.d("MenuViewModel", "getCategories() called")
        getCategories()
        Log.d("MenuViewModel", "getProductList() called")
        getProductList()

    }

    private fun getProductList() {
        viewModelScope.launch {
            Log.e("----------", "2")
            val productList = productUserCase.getProductMenu()
            _menuState.value = menuState.value.copy(productList = productList)
        }
    }

    private fun getCategories() {
         categoriesUseCase.getCategories()
            .onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _menuState.value = _menuState.value.copy(
                            categoriesList = result.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        _menuState.value = _menuState.value.copy(
                            isError = result.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _menuState.value = _menuState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun setBottomSheetCheckBox(checkBoxList: List<Boolean>) {
        productUserCase.list = checkBoxList

        getProductList()

    }
}