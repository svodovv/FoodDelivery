package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fooddelivery.domain.model.CategoriesModel
import com.example.fooddelivery.domain.model.ProductMenuModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CategoriesRow(
    lazyRowState: LazyListState,
    categoriesList: List<CategoriesModel>,
    productList: List<ProductMenuModel>,
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    lazyGridState: LazyGridState?,
    indexButtonInRow: MutableIntState?,
) {
    LazyRow(
        state = lazyRowState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        items(categoriesList) { categoriesModel ->
            val categories = productList.firstOrNull { it.categoryId == categoriesModel.id }
            if (categories != null) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val categoriesIndex =
                                categoriesList.indexOfFirst { it.id == categoriesModel.id }
                            val gridIndex =
                                productList.indexOfFirst { it.categoryId == categoriesModel.id }


                            lazyGridState?.scrollToItem(gridIndex)
                            lazyRowState.animateScrollToItem(categoriesIndex)
                            indexButtonInRow?.intValue = categoriesIndex
                        }

                    },
                    Modifier.padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (indexButtonInRow != null) {
                            if (categoriesList[indexButtonInRow.intValue].id == categoriesModel.id) Color(
                                0xFFF15412
                            )
                            else Color.Transparent
                        } else Color.Transparent

                    )
                ) {
                    Text(text = categoriesModel.name, color = Color.Black)
                }
            }
        }
    }
}