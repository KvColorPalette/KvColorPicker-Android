package com.kavi.droid.color.picker.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kavi.droid.color.picker.ui.pickers.GridColorPicker
import com.kavi.droid.color.picker.ui.pickers.RGBAColorPicker

/**
 * This created a bottom sheet to pick color. As a content of this user can select their color using
 * GridColorPicker or RGBAColorPicker.
 * GridColorPicker: This a color grid with predefined main 16 colors, and each color variances.
 * RGBAColorPicker: This a color picker with RGB and Alpha values. Consumer can create their own color by changing
 * RED, GREEN and BLUE values in a color.
 *
 * @param showSheet: MutableState<Boolean>: State variable to show and hide bottom sheet.
 * @param sheetState: SheetState: State variable to control the bottom sheet.
 * @param onColorSelected: (selectedColor: Color) -> Unit: Callback to invoke when a color is selected.
 *
 * @return @Composable: A bottom sheet UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KvColorPickerBottomSheet(showSheet: MutableState<Boolean>, sheetState: SheetState, onColorSelected: (selectedColor: Color) -> Unit) {
    ModalBottomSheet(
        onDismissRequest = {
            showSheet.value = false
        },
        sheetState = sheetState,
    ) {
        Column {
            var selectedColor by remember { mutableStateOf(Color.Black) }
            var tabIndex by remember { mutableIntStateOf(0) }
            val tabs = listOf("RGB-A", "GRID")

            Text(
                text ="Pick you color",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth().padding(start = 16.dp, end = 16.dp),
                fontSize = 32.sp
            )

            TabRow(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp),
                selectedTabIndex = tabIndex,
                containerColor = Color.Transparent,
                indicator = { tabPositions ->
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[tabIndex]),
                        color = MaterialTheme.colorScheme.secondary,
                        width = 150.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp
                            )
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        selectedContentColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
            when(tabIndex) {
                0 -> RGBAColorPicker(
                    modifier = Modifier.padding(16.dp),
                    onColorSelected = {
                        selectedColor = it
                    }
                )
                1 -> GridColorPicker(
                    modifier = Modifier.padding(16.dp),
                    onColorSelected = {
                        selectedColor = it
                    }
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
            ) {
                OutlinedButton (
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        showSheet.value = false
                    }
                ) {
                    Text(text = "Close", color = MaterialTheme.colorScheme.secondary)
                }

                Button(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        showSheet.value = false
                        onColorSelected.invoke(selectedColor)
                    }
                ) {
                    Text(text = "Select", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }

    }
}