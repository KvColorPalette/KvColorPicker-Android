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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kavi.droid.color.picker.R
import com.kavi.droid.color.picker.ui.common.AlphaSlider
import com.kavi.droid.color.picker.ui.common.ColorSlider

/**
 * A composable function that creates a color picker UI for selecting RGB-A colors. This component
 * contain 3 sliders for adjusting the red, green, and blue values of the color and another slider to adjust the alpha value.
 * By adjusting these values, consumer can select or generate your desired color.
 *
 * @param modifier: Modifier: The modifier to apply to this layout.
 * @param lastSelectedColor: Color: variable to pass last selected color.
 * @param onColorSelected: (selectedColor: Color) -> Unit: Callback to invoke when a color is selected.
 *
 * @return @Composable: A color picker UI for selecting RGB-A colors.
 */
@Composable
fun RGBAColorPicker (
    modifier: Modifier = Modifier,
    lastSelectedColor: Color = Color.White,
    onColorSelected: (selectedColor: Color) -> Unit
) {
    // State variables for RGB-A values
    val red = rememberSaveable { mutableFloatStateOf(lastSelectedColor.red) }
    val green = rememberSaveable { mutableFloatStateOf(lastSelectedColor.green) }
    val blue = rememberSaveable { mutableFloatStateOf(lastSelectedColor.blue) }
    val alpha = rememberSaveable { mutableFloatStateOf(lastSelectedColor.alpha) }

    // Derived state for the color based on RGBA values
    val color by remember {
        derivedStateOf {
            Color(red.floatValue, green.floatValue, blue.floatValue, alpha.floatValue)
        }
    }

    // Launch an effect to invoke the provided callback with the selected color
    LaunchedEffect(color) {
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
                // Sliders for adjusting RGB-A values
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    ColorSlider("RED", red, Color.Red)
                    ColorSlider("GREEN", green, Color.Green)
                    ColorSlider("BLUE", blue, Color.Blue)
                    AlphaSlider(alpha, color)
                }
            }
        }
    }
}