package com.example.fooddelivery.presentation.ui.ItemDescriptionScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.ui.ItemDescriptionScreen.ProductDescriptionViewModel
import com.example.fooddelivery.presentation.ui.ShoppingCart.ShoppingCartState
import com.example.fooddelivery.presentation.ui.ShoppingCart.ShoppingCartViewModel

@Composable
fun ProductDescriptionScreen(
    navController: NavController,
    id: Int,
    shoppingCartState: ShoppingCartState,
    productMenuViewModel: ProductDescriptionViewModel = hiltViewModel(),
) {
    productMenuViewModel.getItemInfo(id)
    val productInfo = productMenuViewModel.productInfo.value.productInfo
    val list = listOf(productInfo)


    Column {
        Box(modifier = Modifier
            .weight(0.9f)
            .wrapContentSize()
            .background(Color(0x08000000)),
            contentAlignment = Alignment.Center) {


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(list) { productInfo ->
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.i_eat),
                            contentDescription = "eat in product description, product id is $id"
                        )
                    }
                    Text(
                        text = productInfo.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 8.dp),
                        style = MaterialTheme.typography.displayLarge

                    )
                    Text(
                        text = productInfo.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 16.dp),
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.Gray
                    )
                    RowInProductDescription(
                        productLabel = stringResource(R.string.measure),
                        productInfo = "${productInfo.measure} ${productInfo.measureUnit}"
                    )
                    RowInProductDescription(
                        productLabel = stringResource(R.string.Energ_chen),
                        productInfo = "${productInfo.energyPer100Grams}"
                    )
                    RowInProductDescription(
                        productLabel = stringResource(R.string.protein),
                        productInfo = "${productInfo.proteinsPer100Grams}"
                    )
                    RowInProductDescription(
                        productLabel = stringResource(R.string.fat),
                        productInfo = "${productInfo.fatsPer100Grams}"
                    )
                    RowInProductDescription(
                        productLabel = stringResource(R.string.carbon),
                        productInfo = "${productInfo.carbohydratesPer100Grams}"
                    )

                }

            }

            Image(painter = painterResource(id = R.drawable.backinproductdescription),
                contentDescription = "back arrow in product description",
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .align(Alignment.TopStart)
                    .padding(16.dp))
        }
        Box(modifier = Modifier.weight(0.1f)) {
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF15412))
            ) {
                Text(
                    text = stringResource(
                        R.string.In_to_the_basket, productInfo.priceCurrent
                    ) + " ₽"
                )
            }
        }
    }
}

@Composable
fun RowInProductDescription(
    productLabel: String, productInfo: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, Color(0x1F000000))
    ) {
        Text(
            text = productLabel, textAlign = TextAlign.Start, modifier = Modifier
                .padding(
                    start = 16.dp, top = 13.dp, bottom = 13.dp, end = 8.dp
                )
                .weight(1f), style = MaterialTheme.typography.displaySmall, color = Color.Gray
        )
        Text(
            text = productInfo,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(top = 13.dp, bottom = 13.dp, end = 16.dp)
                .weight(1f),
            style = MaterialTheme.typography.displaySmall
        )
    }
}