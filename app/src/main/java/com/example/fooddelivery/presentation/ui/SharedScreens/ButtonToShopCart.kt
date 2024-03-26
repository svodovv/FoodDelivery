package com.example.fooddelivery.presentation.ui.SharedScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.omgupsapp.presentation.navigation.Screen

@Composable
fun ButtonToShopCart(
    price: Int, navController: NavController
) {
    Button(
        onClick = { navController.navigate(Screen.ShopCartScreen.route)},
        Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp
            ),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFF15412))
    ) {
        Image(
            painterResource(R.drawable.shop_cart_icon), contentDescription = "asd"
        )
        Text(
            text = " $price ₽"
        )
    }
}