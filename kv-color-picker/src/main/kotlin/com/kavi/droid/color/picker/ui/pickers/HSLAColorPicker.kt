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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.kavi.droid.color.picker.ui.common.AlphaSlider
import com.kavi.droid.color.picker.ui.common.ColorHueSlider
import com.kavi.droid.color.picker.ui.common.ColorSaturationAndLightnessSlider

@Composable
fun HSLAColorPicker(modifier: Modifier = Modifier, onColorSelected: (selectedColor: Color) -> Unit) {

    // State variables for HSL-A values
    val hue = rememberSaveable { mutableFloatStateOf(0f) }
    val saturation = rememberSaveable { mutableFloatStateOf(0f) }
    val lightness = rememberSaveable { mutableFloatStateOf(0f) }
    val alpha = rememberSaveable { mutableFloatStateOf(1f) }

    val colorHex = remember { mutableStateOf(TextFieldValue("")) }

    // Derived state for the color based on HSL-A values
    val color by remember {
        derivedStateOf {
            Color.hsl(hue.floatValue, saturation.floatValue, lightness.floatValue, alpha.floatValue)
        }
    }

    // Launch an effect to invoke the provided callback with the selected color
    LaunchedEffect(color) {
        colorHex.value = TextFieldValue(ColorUtil.getHex(color = color))
        onColorSelected.invoke(color)
    }

    Column (modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Column (
            modifier = Modifier
                .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color.White)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.phrase_select_color_rgba),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 12.dp),
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )

            Row {
                // Sliders for adjusting HSL-A values
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    ColorHueSlider(hue, Color.Red)
                    ColorSaturationAndLightnessSlider("Saturation", saturation, color)
                    ColorSaturationAndLightnessSlider("Lightness", lightness, color)
                    AlphaSlider(alpha, color)
                }
            }
        }
    }
}