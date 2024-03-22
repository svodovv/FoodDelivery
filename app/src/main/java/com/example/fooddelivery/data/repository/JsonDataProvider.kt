package com.example.fooddelivery.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

class JsonDataProvider  @Inject constructor(
    @ApplicationContext private val context: Context
){
    fun <T>loadJsonDataList(resId: Int, type: Type): List<T> {

        val inputStream = context.resources.openRawResource(resId)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val itemType = object : TypeToken<List<T>>() {}.type

        return gson.fromJson(jsonString, type)
    }
}