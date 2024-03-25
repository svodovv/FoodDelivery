package com.example.fooddelivery.presentation.ui.MenuScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.domain.use_case.CategoriesUseCase
import com.example.fooddelivery.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val productUserCase: ProductUseCase,
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {
    private val _menuState = mutableStateOf(MenuState())
    val menuState: State<MenuState> = _menuState


    init {

        getCategories()
        getProductList()

    }

    private fun getProductList() {
        val productList = productUserCase.getProductMenu()
        _menuState.value = menuState.value.copy(productList = productList)
    }

    private fun getCategories() {
        val categories = categoriesUseCase.getCategories()
        _menuState.value = _menuState.value.copy(categoriesList = categories)
    }

    fun setBottomSheetCheckBox(checkBoxList: List<Boolean>) {
        productUserCase.list = checkBoxList

        getProductList()

    }
}