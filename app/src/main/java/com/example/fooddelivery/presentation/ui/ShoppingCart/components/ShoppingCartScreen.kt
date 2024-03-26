package com.example.fooddelivery.presentation.ui.ShoppingCart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.ui.ShoppingCart.ShoppingCartViewModel
import com.omgupsapp.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen(
    navController: NavController, shoppingCartViewModel: ShoppingCartViewModel
) {
    val shoppingCartState =
        shoppingCartViewModel.productsToShoppingCartState.collectAsStateWithLifecycle()
    val keys = shoppingCartState.value.mapProductIdTiPairPriceQuantity.map {
        Pair(
            it.key, it.value.second
        )
    }.toMap()
    shoppingCartViewModel.getSelectedProduct(keys)

    val productListState = shoppingCartViewModel.shoppingCartState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Column(modifier = Modifier.fillMaxHeight().padding(top = 4.dp),
                    verticalArrangement = Arrangement.Center) {


                    Text(
                        text = "Корзина",
                        color = Color.Black,
                        fontSize = 22.sp,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(start = 4.dp, end = 8.dp)
                ) {

                    Image(
                        painterResource(id = R.drawable.arrowleftinsearchinput),
                        contentDescription = "arrow left in shop cart",

                        )
                }
            },
            modifier = Modifier.shadow(elevation = 5.dp),

            )

        if (productListState.value.productList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Пусто, выберите блюда в каталое :)",
                    modifier = Modifier.padding(start = 68.dp, end = 68.dp),
                    color = Color.Gray,
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center

                )
            }
        } else {
            Box(modifier = Modifier.weight(0.9f)) {
                LazyColumn {
                    items(productListState.value.productList) { product ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                                .padding(16.dp)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.i_eat),
                                contentDescription = "eat in shop cart",
                                modifier = Modifier
                                    .size(96.dp)
                                    .padding(end = 16.dp)
                            )
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {

                                Text(
                                    text = product.name,
                                    style = MaterialTheme.typography.displaySmall,
                                    fontSize = 16.sp,
                                    modifier = Modifier.weight(0.5f)
                                )

                                var productQuantity = product.quantity

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.6f)
                                        .height(44.dp),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(0.9f)
                                            .align(Alignment.CenterVertically),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(painter = painterResource(id = R.drawable.minusinshopcart),
                                            contentDescription = "Icon minus",
                                            modifier = Modifier
                                                .clickable {
                                                    productQuantity--
                                                    shoppingCartViewModel.updateProductList(
                                                        Triple(
                                                            product.id,
                                                            product.priceCurrent,
                                                            productQuantity
                                                        )
                                                    )
                                                }
                                                .padding(top = 4.dp)
                                                .fillMaxHeight())

                                        Text(
                                            text = "$productQuantity",
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .padding(12.dp)
                                                .padding(top = 4.dp),
                                            style = MaterialTheme.typography.bodySmall,
                                        )

                                        Image(painter = painterResource(id = R.drawable.plusinshopcart),
                                            contentDescription = "Icon plus",
                                            modifier = Modifier
                                                .clickable {
                                                    productQuantity++
                                                    shoppingCartViewModel.updateProductList(
                                                        Triple(
                                                            product.id,
                                                            product.priceCurrent,
                                                            productQuantity
                                                        )
                                                    )
                                                }
                                                .padding(top = 4.dp)
                                                .fillMaxHeight())
                                    }

                                    Column(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "${product.priceCurrent} ₽",
                                            color = Color.Black,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontSize = 16.sp

                                        )
                                        product.priceOld?.let {
                                            Text(
                                                text = "$it ₽",
                                                color = Color.Gray,
                                                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                                modifier = Modifier.padding(start = 8.dp),
                                                fontSize = 14.sp,
                                                textAlign = TextAlign.End
                                            )
                                        }
                                    }
                                }

                            }

                        }
                        Divider(
                            color = Color(0x1F000000),
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )

                    }

                }
            }
        }
        Box(modifier = Modifier.weight(0.1f)) {
            Button(
                onClick = { navController.navigate(Screen.ShopCartScreen.route) },
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF15412))
            ) {

                Text(
                    text = " Заказать за ${shoppingCartState.value.price} ₽"
                )
            }
        }
    }
}