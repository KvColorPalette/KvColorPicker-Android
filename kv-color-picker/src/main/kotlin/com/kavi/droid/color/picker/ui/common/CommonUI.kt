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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kavi.droid.color.palette.KvColorPalette
import com.kavi.droid.color.palette.model.KvColor
import com.kavi.droid.color.palette.util.ColorUtil
import com.kavi.droid.color.picker.R
import com.kavi.droid.color.picker.extension.toColorRangeInt
import com.kavi.droid.color.picker.extension.toHueRangeInt
import com.kavi.droid.color.picker.extension.toSaturationAndLightnessRangeInt

/**
 * A composable function that creates a slider for adjusting a float value associated with a color.
 *
 * @param colorLabel: String: The label to display alongside the slider.
 * @param colorValueState: MutableState<Float>: The mutable state holding the current color value of the slider.
 * @param color: Color: The color used for the active track of the slider.
 *
 * @return @Composable: A slider UI for color selection.
 */
@Composable
internal fun ColorSlider(colorLabel: String, colorValueState: MutableState<Float>, color: Color) {
    /**
     * Displays a slider for adjusting the given [colorValueState] associated with the provided [colorLabel].
     * The slider's active track color is set to [color].
     */
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = colorLabel,
            color = Color.Black,
            modifier = Modifier.weight(.2f)
        )
        Slider(
            value = colorValueState.value,
            onValueChange = colorValueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = color
            ),
            modifier = Modifier.weight(.8f)
        )
        Text(
            text = colorValueState.value.toColorRangeInt().toString(),
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
 * @param alphaValueState: MutableState<Float>: The mutable state holding the current value of the slider.
 * @param color: Color: The color used for the active track of the slider.
 *
 * @return @Composable: A slider UI for alpha selection.
 */
@Composable
internal fun AlphaSlider(alphaValueState: MutableState<Float>, color: Color) {
    /**
     * Displays a slider for adjusting the given [alphaValueState].
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
            value = alphaValueState.value,
            onValueChange = alphaValueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = color
            ),
            valueRange = 0f..1f,
            modifier = Modifier.weight(.8f),
        )
        Text(
            text = DecimalFormat("#.##").format(alphaValueState.value).toString(),
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
 * A composable function that creates a slider for adjusting a float value associated with a color.
 *
 * @param hueValueState: MutableState<Float>: The mutable state holding the current color value of the slider.
 * @param color: Color: The color used for the active track of the slider.
 *
 * @return @Composable: A slider UI for color selection.
 */
@Composable
internal fun ColorHueSlider(hueValueState: MutableState<Float>, color: Color) {
    /**
     * Displays a slider for adjusting the given [hueValueState]
     * The slider's active track color is set to [color].
     */
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Hue",
            color = Color.Black,
            modifier = Modifier.weight(.2f)
        )
        Slider(
            value = hueValueState.value,
            onValueChange = hueValueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = color
            ),
            modifier = Modifier.weight(.8f)
        )
        Text(
            text = hueValueState.value.toHueRangeInt().toString(),
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
 * @param label: String: The label to display alongside the slider.
 * @param valueState: MutableState<Float>: The mutable state holding the current value of the slider.
 * @param color: Color: The color used for the active track of the slider.
 *
 * @return @Composable: A slider UI for alpha selection.
 */
@Composable
internal fun ColorSaturationAndLightnessSlider(label: String, valueState: MutableState<Float>, color: Color) {
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
            modifier = Modifier.weight(.8f),
        )
        Text(
            text = valueState.value.toSaturationAndLightnessRangeInt().toString(),
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
 * A composable function that displays the selected color and its details.
 *
 * @param color: Color: The selected color to display.
 * @param colorHex: MutableState<TextFieldValue>: The mutable state holding the current color hex value.
 *
 * @return @Composable: A UI to display selected color and its details.
 */
@Composable
internal fun SelectedColorDetail(color: Color, colorHex: MutableState<TextFieldValue>) {
    // Retrieve a ClipboardManager object
    val clipboardManager = LocalClipboardManager.current

    Row (
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        // Display the current color in a Box with a MaterialTheme shape
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, top = 22.dp, bottom = 12.dp)
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
                text = stringResource(R.string.phrase_selected_color),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
            )

            Row {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(.8f),
                    value = colorHex.value,
                    maxLines = 1,
                    label = { Text(text = stringResource(R.string.label_color_hex)) },
                    onValueChange = { newColorHex ->
                        colorHex.value = newColorHex
                    }
                )
                Icon(
                    painter = painterResource(R.drawable.icon_copy),
                    contentDescription = "copy icon",
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .padding(8.dp)
                        .clickable {
                            clipboardManager.setText(colorHex.value.annotatedString)
                        }
                )
            }
        }
    }
}

/**
 * A composable function that displays a color colum with predefined colors.
 *
 * @param givenColor: KvColor: The color to generate color palette. This is from [KvColorPalette]
 * @param selectedColor: Color: The selected color to highlight.
 * @param onSelect: (color: Color) -> Unit: Callback to invoke when a color is selected.
 */
@Composable
internal fun ColorColum(givenColor: KvColor, selectedColor: Color, onSelect: (color: Color) -> Unit) {
    val colors = KvColorPalette.instance.generateColorPalette(givenColor = givenColor)
    Column {
        colors.forEach {
            ColorBox(givenColor = it, selectedColor = selectedColor, onSelect = onSelect)
        }
    }
}

/**
 * A composable function that displays a single color box.
 *
 * @param givenColor: Color: The color to display.
 * @param selectedColor: Color: The selected color to highlight.
 * @param onSelect: (color: Color) -> Unit: Callback to invoke when a color is selected.
 */
@Composable
internal fun ColorBox(givenColor: Color, selectedColor: Color?, onSelect: (color: Color) -> Unit) {
    var isSelected by remember { mutableStateOf(false) }

    selectedColor?.let {
        isSelected = ColorUtil.getHexWithAlpha(givenColor) == ColorUtil.getHexWithAlpha(it)
    }

    Box(
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
            .background(givenColor, RectangleShape)
            .clickable {
                isSelected = true
                onSelect(givenColor)
            }
            .then(if (isSelected) Modifier.border(2.dp, Color.White) else Modifier)
    )
}
