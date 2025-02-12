package com.kavi.droid.color.picker.ui.pickers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kavi.droid.color.palette.color.MatPackage
import com.kavi.droid.color.picker.ui.common.ColorColum

@Composable
fun GridColorPicker(modifier: Modifier = Modifier, onColorSelected: (selectedColor: Color) -> Unit) {

    var selectedColor by remember { mutableStateOf(Color.White) }
    val colorHex = remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ColorColum(givenColor = MatPackage.MatRed, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatRose, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatLPurple, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatDPurple, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatDBlue, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatLBlue, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatLLBlue, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatLCyan, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatDCyan, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatDGreen, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatLGreen, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatLLGreen, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatYellow, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatGold, selectedColor = selectedColor) { color -> selectedColor = color }
        ColorColum(givenColor = MatPackage.MatOrange, selectedColor = selectedColor) { color -> selectedColor = color }
    }
}