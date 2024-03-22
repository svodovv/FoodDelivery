package com.example.fooddelivery.domain.use_case

import android.util.Log
import com.example.fooddelivery.data.remote.dto.toCategoriesListModel
import com.example.fooddelivery.data.remote.dto.toProductMenuModel
import com.example.fooddelivery.data.repository.DataRepositoryImpl
import javax.inject.Inject

class CategoriesUseCase  @Inject constructor(
    private val dataRepositoryImpl: DataRepositoryImpl
) {

    fun getCategories() = dataRepositoryImpl.getCategoriesList().map { it ->
        it.toCategoriesListModel()
    }

}