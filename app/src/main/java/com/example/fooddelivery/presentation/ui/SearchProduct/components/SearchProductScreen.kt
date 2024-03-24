package com.example.fooddelivery.presentation.ui.SearchProduct.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.ui.MenuScreen.components.ProductGrid
import com.example.fooddelivery.presentation.ui.SearchProduct.SearchProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchProductScreen(
    navController: NavController,
    searchProductViewModel: SearchProductViewModel = hiltViewModel(),
) {
    val searchProductList = searchProductViewModel.searchProductState.value
    val rememberLazyGrid = rememberLazyGridState()



    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBarInSearchScreen(
            navController = navController, searchProductViewModel = searchProductViewModel
        )
        if (searchProductList.inputText.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.insert_product_name),
                    modifier = Modifier.padding(start = 68.dp, end = 68.dp),
                    color = Color.Gray,
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            if (searchProductList.productList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.search_product_is_empty),
                        modifier = Modifier.padding(start = 68.dp, end = 68.dp),
                        color = Color.Gray,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
            ProductGrid(
                lazyGridState = rememberLazyGrid,
                productList = searchProductList.productList,
                gridItemIndex = null,
                navController = navController
            )
        }
    }
}