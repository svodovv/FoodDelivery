@file:Suppress("NAME_SHADOWING")

package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.ui.ShoppingCart.ShoppingCartViewModel

@Composable
fun ButtonInLazyGrid(
    priceCurrent: Int,
    priceOld: Int?,
    productId: Int,
    shoppingCartViewModel: ShoppingCartViewModel,
    modifier: Modifier = Modifier
) {
    val shoppingCartState =
        shoppingCartViewModel.productsToShoppingCartState.collectAsStateWithLifecycle().value
    val productIdInMap = shoppingCartState.mapProductIdTiPairPriceQuantity[productId]
    if (productIdInMap == null) {
        Button(
            onClick = {
                shoppingCartViewModel.updateProductList(
                    Triple(
                        productId, priceCurrent, 1
                    )
                )
            }, modifier = modifier, shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "$priceCurrent ₽",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium

            )
            if (priceOld != null) {
                Text(
                    text = "$priceOld ₽",
                    color = Color.Gray,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(start = 8.dp),
                    textAlign = TextAlign.End
                )
            }
        }
    } else {
        var productQuantity = productIdInMap.second
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(painter = painterResource(id = R.drawable.minus_button),
                contentDescription = "Icon minus",
                modifier = Modifier.clickable {
                    productQuantity--
                    shoppingCartViewModel.updateProductList(
                        Triple(
                            productId, priceCurrent, productQuantity
                        )
                    )
                })

            Text(
                text = "$productQuantity",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(12.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium,
            )

            Image(painter = painterResource(id = R.drawable.plus_button),
                contentDescription = "Icon plus",
                modifier = Modifier.clickable {
                    productQuantity++
                    shoppingCartViewModel.updateProductList(
                        Triple(
                            productId, priceCurrent, productQuantity
                        )
                    )
                })
        }
    }
}