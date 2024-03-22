package com.example.fooddelivery.presentation.ui.MenuScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val productUserCase: ProductUseCase
):ViewModel()  {
    private val _menuState = mutableStateOf(MenuState())
    val menuState: State<MenuState> = _menuState

    init {
        getProductList()
    }

    private fun getProductList(){
    val productList = productUserCase.getProductList()
        _menuState.value = menuState.value.copy(productList = productList)
    }
}