package com.example.fooddelivery.presentation.ui.MenuScreen.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.ui.MenuScreen.MenuViewModel
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    menuViewModel: MenuViewModel = hiltViewModel()
) {
    val menuState = menuViewModel.menuState.value

    val lazyGridState = rememberLazyGridState()
    val lazyRowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val categoriesId = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        snapshotFlow { menuState.productList[categoriesId.intValue].categoryId }.collect { id ->
            val indexInCategories = menuState.categoriesList.indexOfFirst { it.id == id }
            lazyRowState.animateScrollToItem(indexInCategories)
            Log.e("index", indexInCategories.toString())
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            state = lazyRowState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .align(Alignment.Start),
        ) {
            items(menuState.categoriesList) { categoriesList ->

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val index =
                                menuState.productList.indexOfFirst { it.categoryId == categoriesList.id }
                            lazyGridState.animateScrollToItem(index)
                        }
                    },
                    Modifier.padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                        if (categoriesId.intValue == categoriesList.id) {
                            Color(0xFFF15412)
                        } else Color.Transparent
                    )
                ) {
                    Text(text = categoriesList.name, color = Color.Black)

                }
            }
        }

        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
        ) {
            itemsIndexed(menuState.productList) { index, product ->
                categoriesId.intValue = index
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(290.dp)
                        .width(167.5.dp),
                ) {
                    Column {
                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.i_eat),
                                contentDescription = "Eat image }",
                                modifier = Modifier

                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start)
                        ) {
                            Text(
                                text = product.name,
                                modifier = Modifier.padding(
                                    start = 12.dp, end = 12.dp, top = 12.dp, bottom = 4.dp
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "${product.measure} ${product.measureUnit}",
                                modifier = Modifier.padding(
                                    start = 12.dp, end = 12.dp, bottom = 4.dp
                                ),
                                color = Color.Gray
                            )
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(12.dp)
                                    .weight(1f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "${product.priceCurrent} ₽",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodyMedium

                                )
                                if (product.priceOld != null) {
                                    Text(
                                        text = "${product.priceOld} ₽",
                                        color = Color.Gray,
                                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                        modifier = Modifier
                                            .align(Alignment.Bottom)
                                            .padding(start = 8.dp),
                                        textAlign = TextAlign.End
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
