package com.example.fooddelivery.presentation.ui.MenuScreen.components

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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun MenuScreen(
    menuViewModel: MenuViewModel = hiltViewModel()
) {
    val menuState = menuViewModel.menuState.value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(){

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
        ) {
            items(menuState.productList) { product ->
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
                                    start = 12.dp,
                                    end = 12.dp,
                                    top = 12.dp,
                                    bottom = 4.dp
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "${product.measure} ${product.measureUnit}",
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    end = 12.dp,
                                    bottom = 4.dp
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