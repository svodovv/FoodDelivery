package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.example.fooddelivery.domain.model.ProductMenuModel
import com.example.fooddelivery.presentation.ui.ShoppingCart.ShoppingCartViewModel
import com.omgupsapp.presentation.navigation.Screen

@Composable
fun ProductGrid(
    lazyGridState: LazyGridState,
    productList: List<ProductMenuModel>,
    gridItemIndex: MutableIntState?,
    navController: NavController,
    shoppingCartViewModel: ShoppingCartViewModel
) {
    LazyVerticalGrid(
        state = lazyGridState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
            .background(Color(0x05000000)),

        ) {
        itemsIndexed(productList) { index, product ->

            gridItemIndex?.intValue = index
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .height(290.dp)
                    .width(167.5.dp),

                ) {
                Column {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .clickable {
                            navController.navigate(
                                Screen.MenuScreen.route + "/{itemId}".replace(
                                    oldValue = "{itemId}", newValue = product.id.toString()
                                )
                            )
                        }

                    ) {
                        Box {

                            Tag(
                                product.tagIds,
                                modifier = Modifier.padding(8.dp),
                                saleTag = product.priceOld != null
                            )

                            Image(
                                painter = painterResource(id = R.drawable.i_eat),
                                contentDescription = "Eat image }",
                                modifier = Modifier

                            )
                        }

                        Column(
                            modifier = Modifier.padding(
                                start = 12.dp, end = 12.dp, bottom = 12.dp
                            )
                        ) {


                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "${product.measure} ${product.measureUnit}",
                                modifier = Modifier.padding(
                                    top = 4.dp
                                ),
                                color = Color.Gray
                            )
                        }
                    }

                    ButtonInLazyGrid(
                        priceCurrent = product.priceCurrent,
                        priceOld = product.priceOld,
                        productId = product.id,
                        shoppingCartViewModel = shoppingCartViewModel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 12.dp, bottom = 12.dp, end = 12.dp, top = 0.dp),
                    )
                }
            }
        }
    }
}