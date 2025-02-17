package com.kavi.droid.color.picker.ui.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kavi.droid.color.palette.color.MatPackage
import com.kavi.droid.color.palette.util.ColorUtil
import com.kavi.droid.color.picker.R
import com.kavi.droid.color.picker.ui.common.ColorColum

/**
 * A composable function that creates a grid grid to select colors. This color grid is created with
 * 16 predefined major colors and those color's 10 color variances.
 *
 * @param modifier: Modifier: The modifier to apply to this layout.
 * @param onColorSelected: (selectedColor: Color) -> Unit: Callback to invoke when a color is selected.
 *
 * @return @Composable: A grid UI to select colors.
 */
@Composable
fun GridColorPicker(modifier: Modifier = Modifier, onColorSelected: (selectedColor: Color) -> Unit) {

    var selectedColor by remember { mutableStateOf(Color.White) }
    val colorHex = remember { mutableStateOf(TextFieldValue("#ffffff")) }

    val onSelectColor: (color: Color) -> Unit = {
        selectedColor = it
        colorHex.value = TextFieldValue(ColorUtil.getHex(it))
    }

    // Launch an effect to invoke the provided callback with the selected color
    LaunchedEffect(selectedColor) {
        onColorSelected.invoke(selectedColor)
    }

    Column (modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Column(
            modifier = Modifier
                .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color.White)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.phrase_select_color_grid),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 12.dp),
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 4.dp, end = 4.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ColorColum(
                    givenColor = MatPackage.MatRed,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatRose,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatLPurple,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatDPurple,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatDBlue,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatLBlue,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatLLBlue,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatLCyan,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatDCyan,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatDGreen,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatLGreen,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatLLGreen,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatYellow,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatGold,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
                ColorColum(
                    givenColor = MatPackage.MatOrange,
                    selectedColor = selectedColor,
                    onSelect = onSelectColor
                )
            }
        }
    }
}