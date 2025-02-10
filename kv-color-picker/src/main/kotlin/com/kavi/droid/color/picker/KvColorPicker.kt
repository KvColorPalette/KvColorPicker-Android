package com.kavi.droid.color.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kavi.droid.color.palette.app.extension.toColorInt
import com.kavi.droid.color.palette.util.ColorUtil

@Composable
fun KvColorPicker(modifier: Modifier = Modifier, onColorSelected: (selectedColor: Color) -> Unit) {
    // State variables for RGB values
    val red = rememberSaveable { mutableFloatStateOf(0f) }
    val green = rememberSaveable { mutableFloatStateOf(0f) }
    val blue = rememberSaveable { mutableFloatStateOf(0f) }

    var colorHex by remember { mutableStateOf(TextFieldValue("")) }
    var hasTextFieldFocus by remember { mutableStateOf(false) }

    // Retrieve a ClipboardManager object
    val clipboardManager = LocalClipboardManager.current

    // Derived state for the color based on RGBA values
    val color by remember {
        derivedStateOf {
            Color(red.floatValue, green.floatValue, blue.floatValue)
        }
    }

    // Launch an effect to invoke the provided callback with the selected color
    LaunchedEffect(color) {
        colorHex = TextFieldValue(ColorUtil.getHex(color = color))
        onColorSelected.invoke(color)
    }

    Column (
        modifier = modifier
            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White)
            .padding(12.dp)
    ) {
        Row {
            // Sliders for adjusting RGB values
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                ColorSlider("R", red, Color.Red)
                ColorSlider("G", green, Color.Green)
                ColorSlider("B", blue, Color.Blue)
            }

            Column {
                // Display the current color in a Box with a MaterialTheme shape
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp, top = 20.dp, end = 12.dp)
                            .height(80.dp)
                            .width(80.dp)
                            .background(color, shape = MaterialTheme.shapes.large)
                            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                    )
                }
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                modifier = Modifier
                    .padding(1.dp)
                    .weight(1f)
                    .onFocusChanged {
                        hasTextFieldFocus = it.isFocused
                    },
                value = colorHex,
                maxLines = 1,
                label = { Text(text = "Color Hex") },
                onValueChange = { newColorHex ->
                    colorHex = newColorHex
                }
            )

            Button(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                    .height(50.dp)
                    .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        if (hasTextFieldFocus) {
                            val setColor = ColorUtil.getColorFromHex(colorHex.text)
                            red.floatValue = setColor.red
                            green.floatValue = setColor.green
                            blue.floatValue = setColor.blue
                        } else {
                            clipboardManager.setText(colorHex.annotatedString)
                        }
                    }
            ) {
                Text(text = if(hasTextFieldFocus) "Set" else "Copy")
            }
        }
    }
}

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
        Text(text = label, color = Color.Black)
        Slider(
            value = valueState.value,
            onValueChange = valueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = color
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valueState.value.toColorInt().toString(),
            modifier = Modifier.width(25.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorPickerUIPreview() {
    KvColorPicker(onColorSelected = {})
}