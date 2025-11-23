package com.kavi.droid.color.picker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kavi.droid.color.palette.util.ColorUtil
import com.kavi.droid.color.picker.R
import com.kavi.droid.color.picker.ui.common.SelectedColorDetail
import com.kavi.droid.color.picker.ui.pickers.BlendColorPicker
import com.kavi.droid.color.picker.ui.pickers.GridColorPicker
import com.kavi.droid.color.picker.ui.pickers.HSLAColorPicker
import com.kavi.droid.color.picker.ui.pickers.RGBAColorPicker

/**
 * This created a bottom sheet to pick color. As a content of this user can select their color using
 * GridColorPicker or RGBAColorPicker.
 * GridColorPicker: This a color grid with predefined main 16 colors, and each color variances.
 * RGBAColorPicker: This a color picker with RGB and Alpha values. Consumer can create their own color by changing
 * RED, GREEN and BLUE values in a color.
 *
 * @param lastSelectedColor: Color: variable to pass last selected color.
 * @param showSheet: MutableState<Boolean>: State variable to show and hide bottom sheet.
 * @param sheetState: SheetState: State variable to control the bottom sheet.
 * @param onColorSelected: (selectedColor: Color) -> Unit: Callback to invoke when a color is selected.
 *
 * @return @Composable: A bottom sheet UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KvColorPickerBottomSheet(
    lastSelectedColor: Color = Color.White,
    showSheet: MutableState<Boolean>,
    sheetState: SheetState,
    onColorSelected: (selectedColor: Color) -> Unit) {
    ModalBottomSheet (
        onDismissRequest = {
            showSheet.value = false
        },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background,
        scrimColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
    ) {
        Column {
            var selectedColor by remember { mutableStateOf(lastSelectedColor) }
            val colorHex = remember { mutableStateOf(TextFieldValue("#000000")) }
            var tabIndex by remember { mutableIntStateOf(0) }
            val tabs = listOf(
                stringResource(R.string.label_rgba),
                stringResource(R.string.label_grid),
                stringResource(R.string.label_hsla),
                stringResource(R.string.label_blend)
            )

            Text(
                text = stringResource(R.string.title_pick_color),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                fontSize = 36.sp
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
                    lastSelectedColor = selectedColor,
                    onColorSelected = {
                        selectedColor = it
                        colorHex.value = TextFieldValue(ColorUtil.getHex(it))
                    }
                )
                1 -> GridColorPicker(
                    modifier = Modifier.padding(16.dp),
                    lastSelectedColor = selectedColor,
                    onColorSelected = {
                        selectedColor = it
                        colorHex.value = TextFieldValue(ColorUtil.getHex(it))
                    }
                )
                2 -> HSLAColorPicker(
                    modifier = Modifier.padding(16.dp),
                    lastSelectedColor = selectedColor,
                    onColorSelected = {
                        selectedColor = it
                        colorHex.value = TextFieldValue(ColorUtil.getHex(it))
                    }
                )
                3 -> BlendColorPicker(
                    modifier = Modifier.padding(16.dp),
                    lastSelectedColor = selectedColor,
                    onColorSelected = {
                        selectedColor = it
                        colorHex.value = TextFieldValue(ColorUtil.getHex(it))
                    }
                )
            }

            Column (
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                    .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                SelectedColorDetail(color = selectedColor, colorHex = colorHex)

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
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
                        Text(text = stringResource(R.string.label_close), color = MaterialTheme.colorScheme.secondary)
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
                        Text(text = stringResource(R.string.label_select), color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }

    }
}