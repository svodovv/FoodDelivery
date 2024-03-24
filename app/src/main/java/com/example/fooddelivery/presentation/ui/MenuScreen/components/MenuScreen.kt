package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fooddelivery.presentation.ui.MenuScreen.MenuViewModel

@Composable
fun MenuScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    menuViewModel: MenuViewModel = hiltViewModel(),
) {
    val menuState = menuViewModel.menuState.value

    val lazyGridState = rememberLazyGridState()
    val lazyRowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val categoriesId = remember {
        mutableIntStateOf(0)
    }
    val indexButtonInRow = remember {
        mutableIntStateOf(0)
    }

    /*
    Вызываеться только при пролистывании LazyGrid
    Когда мы вращаем список, изменяем индекст отображаемой кнопки в Row
    а также перелистываем до этой кнопки
    */
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { menuState.productList[categoriesId.intValue].categoryId }.collect { categoriesId ->
            val indexInCategories = menuState.categoriesList.indexOfFirst { it.id == categoriesId }
            indexButtonInRow.intValue = indexInCategories
            lazyRowState.animateScrollToItem(indexInCategories)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*
              LazyRow в котором можно переходить по наименованиям блюд
              */
        CategoriesRow(
            lazyRowState = lazyRowState,
            menuState = menuState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .align(Alignment.Start),
            coroutineScope = coroutineScope,
            lazyGridState = lazyGridState,
            indexButtonInRow = indexButtonInRow,

            )
        /*
             LazyGrid в котором содержиться весь список блюд
          */
        ProductGrid(
            lazyGridState = lazyGridState,
            menuState = menuState,
            categoriesId = categoriesId,
            navController = navController
        )
    }
}
