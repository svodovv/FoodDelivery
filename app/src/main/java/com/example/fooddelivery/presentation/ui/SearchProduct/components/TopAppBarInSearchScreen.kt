package com.example.fooddelivery.presentation.ui.SearchProduct.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.example.fooddelivery.R
import com.example.fooddelivery.presentation.ui.SearchProduct.SearchProductState
import com.example.fooddelivery.presentation.ui.SearchProduct.SearchProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarInSearchScreen (
    navController: NavController ,
    searchProductViewModel: SearchProductViewModel
){

    val searchProductList = searchProductViewModel.searchProductState
    TopAppBar(title = {
        OutlinedTextField(value = searchProductList.value.inputText ?: "",
            onValueChange = { searchProductViewModel.onChangeInputSearch(it) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                focusedSupportingTextColor = Color.Transparent,
                focusedLeadingIconColor = Color.Transparent,
                focusedTrailingIconColor = Color.Transparent,
                cursorColor = Color.Transparent,


                ),
            textStyle = MaterialTheme.typography.displayMedium,
            modifier = Modifier.fillMaxWidth().padding(),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text("Найти блюдо") },
            leadingIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Image(
                        painterResource(id = R.drawable.arrowleftinsearchinput),
                        contentDescription = "top app bar settings",
                    )
                }
            },
            trailingIcon = {
                if (searchProductList.value.inputText.isNotBlank()) {
                    IconButton(onClick = {
                        searchProductViewModel.deleteInputText()
                    }, ) {
                        Image(
                            painterResource(id = R.drawable.deletesearchtextinput),
                            contentDescription = "top app bar settings"
                        )
                    }
                }
            })
    })

}