package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.fooddelivery.R

@Composable
fun Tag(
    tagsList: List<Int>, saleTag: Boolean, modifier: Modifier = Modifier
) {

    Row(modifier = modifier) {
        tagsList.forEach { tag ->
            Image(
                painter = painterResource(
                    id =
//По скольку не было в Figme других иконок пришлось обойтись 3мя
                    when (tag) {
                        4 -> {
                            R.drawable.tag_hot
                        }

                        2 -> {
                            R.drawable.tag_vegan
                        }

                        3 -> {
                            R.drawable.tag_hot
                        }

                        else -> R.drawable.tag_sale
                    }

                ), contentDescription = "tag is $tag"
            )
        }
        if (saleTag) {
            Image(
                painter = painterResource(id = R.drawable.tag_sale),
                "sale tag",
            )
        }
    }

}