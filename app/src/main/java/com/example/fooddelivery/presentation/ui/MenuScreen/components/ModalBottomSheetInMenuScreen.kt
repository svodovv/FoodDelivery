package com.example.fooddelivery.presentation.ui.MenuScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.presentation.ui.MenuScreen.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetInMenuScreen(
    checkBoxListState: SnapshotStateList<Pair<String, Boolean>>,
    sheetState: SheetState,
    menuViewModel: MenuViewModel,
    checkBoxQuantity: MutableState<Int?>,
    isSheetOpenOnClick: () -> Unit
) {
    ModalBottomSheet(sheetState = sheetState, onDismissRequest = {
        isSheetOpenOnClick()

    }, dragHandle = {}) {
        Column {
            Text(
                text = "Подобрать блюда", modifier = Modifier.padding(
                    top = 32.dp, start = 24.dp, end = 24.dp, bottom = 16.dp
                ), style = MaterialTheme.typography.bodyMedium, fontSize = 20.sp
            )
            Column(
                modifier = Modifier.padding(
                    start = 24.dp, bottom = 16.dp, end = 24.dp
                )
            ) {
                // ...
                checkBoxListState.forEachIndexed { index, pair ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            checkBoxListState[index] = Pair(pair.first, !pair.second)
                        }) {
                        Text(
                            text = pair.first,
                            Modifier
                                .weight(0.5f)
                                .align(Alignment.CenterVertically),
                            fontSize = 16.sp

                        )
                        Box(modifier = Modifier.weight(0.5f)) {
                            Checkbox(
                                checked = pair.second,
                                onCheckedChange = {
                                    checkBoxListState[index] = Pair(pair.first, it)
                                },
                                modifier = Modifier.align(Alignment.CenterEnd),
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFFF15412),
                                    uncheckedColor = Color(0x1F000000)
                                )
                            )
                        }
                    }
                    if (index != 2) {
                        Divider(
                            color = Color(0x1F000000),
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Button(
                    onClick = {
                        //Отправляем в viewModel -> ProductUseCase чтобы вернуть новый список
                        menuViewModel.setBottomSheetCheckBox(checkBoxListState.toList().map {
                            it.second
                        })
                        //Колличество отмеченных checkBox для label в topAppBar
                        checkBoxQuantity.value =
                            checkBoxListState.toList().map { it.second }.filter { it }.size
                        isSheetOpenOnClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 32.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF15412)
                    )
                ) {
                    Text(
                        text = "Готово",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}