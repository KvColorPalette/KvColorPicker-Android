package com.kavi.droid.color.picker.ui.common

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kavi.droid.color.palette.KvColorPalette
import com.kavi.droid.color.palette.model.KvColor
import com.kavi.droid.color.palette.util.ColorUtil
import com.kavi.droid.color.picker.extension.toColorInt

/**
 * A composable function that creates a slider for adjusting a float value associated with a color.
 *
 * @param label The label to display alongside the slider.
 * @param valueState The mutable state holding the current value of the slider.
 * @param color The color used for the active track of the slider.
 */
@Composable
fun ColorSlider(
    label: String,
    valueState: MutableState<Float>,
    color: Color,
) {
    /**
     * Displays a slider for adjusting the given [valueState] associated with the provided [label].
     * The slider's active track color is set to [color].
     */
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = label,
            color = Color.Black,
            modifier = Modifier.weight(.2f)
        )
        Slider(
            value = valueState.value,
            onValueChange = valueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = color
            ),
            modifier = Modifier.weight(.8f)
        )
        Text(
            text = valueState.value.toColorInt().toString(),
            modifier = Modifier
                .width(25.dp)
                .weight(.1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}

/**
 * A composable function that creates a slider for adjusting a float value associated with a color alpha valuw.
 *
 * @param valueState The mutable state holding the current value of the slider.
 * @param color The color used for the active track of the slider.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlphaSlider(
    valueState: MutableState<Float>,
    color: Color
) {
    /**
     * Displays a slider for adjusting the given [valueState].
     * The slider's active track color is set to [color].
     */
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "ALPHA",
            color = Color.Black,
            modifier = Modifier.weight(.2f)
        )
        Slider(
            value = valueState.value,
            onValueChange = valueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = color
            ),
            valueRange = 0f..1f,
            modifier = Modifier.weight(.8f),
        )
        Text(
            text = DecimalFormat("#.##").format(valueState.value).toString(),
            modifier = Modifier
                .width(25.dp)
                .weight(.1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}

@Composable
fun SelectedColorDetail(
    color: Color,
    colorHex: MutableState<TextFieldValue>
) {
    // Retrieve a ClipboardManager object
    val clipboardManager = LocalClipboardManager.current

    Row {
        // Display the current color in a Box with a MaterialTheme shape
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .height(75.dp)
                    .width(75.dp)
                    .background(color, shape = MaterialTheme.shapes.large)
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Some Text Detail description",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )

            Row {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(.6f),
                    value = colorHex.value,
                    maxLines = 1,
                    label = { Text(text = "Color Hex") },
                    onValueChange = { newColorHex ->
                        colorHex.value = newColorHex
                    }
                )

                Button(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 10.dp)
                        .height(55.dp)
                        .weight(.4f),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        clipboardManager.setText(colorHex.value.annotatedString)
                    }
                ) {
                    Text(text = "Copy")
                }
            }
        }
    }
}

@Composable
fun ColorColum(givenColor: KvColor, selectedColor: Color, onSelect: (color: Color) -> Unit) {
    val colors = KvColorPalette.instance.generateColorPalette(givenColor = givenColor)
    Column {
        colors.forEach {
            ColorBox(givenColor = it, selectedColor = selectedColor, onSelect = onSelect)
        }
    }
}

@Composable
fun ColorBox(givenColor: Color, selectedColor: Color?, onSelect: (color: Color) -> Unit) {
    var isSelected by remember { mutableStateOf(false) }

    selectedColor?.let {
        isSelected = ColorUtil.getHexWithAlpha(givenColor) == ColorUtil.getHexWithAlpha(it)
    }

    Box(
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .background(givenColor, RectangleShape)
            .clickable {
                isSelected = true
                onSelect(givenColor)
            }
            .then(if (isSelected) Modifier.border(2.dp, Color.White) else Modifier)
    )
}
