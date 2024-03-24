package com.example.fooddelivery.presentation.ui.MenuScreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.fooddelivery.presentation.ui.MenuScreen.MenuState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CategoriesRow(
    lazyRowState: LazyListState,
    menuState: MenuState,
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    lazyGridState: LazyGridState,
    indexButtonInRow: MutableIntState,
) {
    LazyRow(
        state = lazyRowState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        items(menuState.categoriesList) { categoriesList ->

            Button(
                onClick = {
                    coroutineScope.launch {
                        val index =
                            menuState.categoriesList.indexOfFirst { it.id == categoriesList.id }
                        delay(100)
                        if (menuState.productList[index].name.isNotBlank()) {
                            Log.e("Name", menuState.categoriesList[index].name)
                            lazyGridState.scrollToItem(index)
                            indexButtonInRow.intValue= index
                            lazyRowState.animateScrollToItem(index)


                        }
                    }
                },
                Modifier.padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (menuState.categoriesList[indexButtonInRow.intValue].id == categoriesList.id)
                        Color(0xFFF15412)
                    else Color.Transparent

                )
            ) {
                Text(text = categoriesList.name, color = Color.Black)
            }
        }
    }
}