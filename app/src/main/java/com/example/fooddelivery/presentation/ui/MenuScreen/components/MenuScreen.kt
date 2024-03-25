package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fooddelivery.presentation.ui.MenuScreen.MenuViewModel
import com.omgupsapp.presentation.scaffold.TopAppBarComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navController: NavHostController,
    menuViewModel: MenuViewModel = hiltViewModel(),
) {
    val menuState = menuViewModel.menuState.value

    val lazyGridState = rememberLazyGridState()
    val lazyRowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val gridItemIndex = remember {
        mutableIntStateOf(0)
    }
    val indexButtonInRow = remember {
        mutableIntStateOf(0)
    }


    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val checkBoxListState = remember {
        mutableStateListOf(
            Pair("Без мяса", false), Pair("Острое", false), Pair("Со скидкой", false)
        )
    }
    val checkBoxQuantity = remember {
        mutableStateOf<Int?>(null)
    }

    /*
    Вызываеться только при пролистывании LazyGrid
    Когда мы вращаем список, изменяем индекст отображаемой кнопки в Row
    а также перелистываем до этой кнопки
    */
    LaunchedEffect(key1 = Unit) {
        snapshotFlow { menuState.productList[gridItemIndex.intValue] }.collect { product ->
            val indexInCategories = menuState.categoriesList.indexOfFirst {
                it.id == product.categoryId
            }
            indexButtonInRow.intValue = indexInCategories
            lazyRowState.animateScrollToItem(indexInCategories)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {


        /*
        coroutineScope и lazyGridState передаю для того,
        чтобы можно было по клику на иконку перейти к началу grid
         */

        TopAppBarComposable(navController = navController,
            lazyGridState = lazyGridState,
            coroutineScope = coroutineScope,
            onClick = { isSheetOpen = true },
            checkBoxQuantity = checkBoxQuantity.value
        )

        /*
            LazyRow в котором можно переходить по категориям блюд
            */
        CategoriesRow(
            lazyRowState = lazyRowState,
            categoriesList = menuState.categoriesList,
            productList = menuState.productList,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .align(Alignment.Start),
            coroutineScope = coroutineScope,
            lazyGridState = lazyGridState,
            indexButtonInRow = indexButtonInRow,

            )/*
             LazyGrid в котором содержиться весь список блюд
          */
        if (menuState.productList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Таких блюд нет :( Попробуйте изменить фильтры",
                    modifier = Modifier.padding(start = 68.dp, end = 68.dp),
                    color = Color.Gray,
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center

                )
            }
        } else {
            ProductGrid(
                lazyGridState = lazyGridState,
                productList = menuState.productList,
                gridItemIndex = gridItemIndex,
                navController = navController
            )
        }
        if (isSheetOpen) {
            ModalBottomSheetInMenuScreen(
                checkBoxListState = checkBoxListState,
                sheetState = sheetState,
                isSheetOpenOnClick = { isSheetOpen = false },
                menuViewModel = menuViewModel,
                checkBoxQuantity =  checkBoxQuantity
                )
        }
    }
}
