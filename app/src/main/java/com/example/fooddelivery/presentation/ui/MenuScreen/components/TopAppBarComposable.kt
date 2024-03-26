package com.omgupsapp.presentation.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.omgupsapp.presentation.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(
    navController: NavController,
    lazyGridState: LazyGridState,
    coroutineScope: CoroutineScope,
    onClick: () -> Unit,
    checkBoxQuantity: Int?
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logo), "logo in top app bar",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 8.dp)
                        .height(48.dp)
                        .clickable {
                            coroutineScope.launch {
                                lazyGridState.animateScrollToItem(index = 1)
                            }
                        }
                )
            }

        },
        navigationIcon = {
            BadgedBox(
                badge = {
                    if ( checkBoxQuantity != 0 && checkBoxQuantity != null ) {
                        Badge(containerColor = Color(0xFFF15412)) {
                            Text(text = checkBoxQuantity.toString())
                        }
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp,)
                    .clickable { onClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.top_app_bar_settings),
                    contentDescription = "top app bar settings"
                )

            }

        },
        actions = {
            IconButton(onClick =
            { navController.navigate(Screen.SearchProductScreen.route) },) {
                Image(
                    painter = painterResource(id = R.drawable.top_app_bar_search),
                    contentDescription = "top app bar search"
                )
            }
        }
    )
}
