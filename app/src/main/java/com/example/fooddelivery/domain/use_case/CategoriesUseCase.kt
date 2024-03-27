package com.example.fooddelivery.domain.use_case

import android.util.Log
import com.example.fooddelivery.common.Resource
import com.example.fooddelivery.common.Resource.Success
import com.example.fooddelivery.data.remote.dto.toCategoriesListModel
import com.example.fooddelivery.data.repository.DataRepositoryImpl
import com.example.fooddelivery.domain.model.CategoriesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val dataRepositoryImpl: DataRepositoryImpl
) {

    fun getCategories(): Flow<Resource<List<CategoriesModel>>> = flow {
        try {

            emit(Resource.Loading())
            val categoriesList = dataRepositoryImpl.getCategoriesList()
                .map {
                    it.toCategoriesListModel()
                }
            emit(Success(categoriesList))

        } catch (e: HttpException) {
            emit(Resource.Error("Ошибка подключения к интернету, возможно, вызвана активным VPN-сервисом."))
            Log.e("csrfToken", e.localizedMessage ?: "")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ""))
            Log.e("csrfToken", e.localizedMessage ?: "")
        }
    }



}