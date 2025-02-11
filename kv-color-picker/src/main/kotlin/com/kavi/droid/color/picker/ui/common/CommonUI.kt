package com.kavi.droid.color.picker.ui.common

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kavi.droid.color.palette.app.extension.toColorInt
import com.kavi.droid.color.picker.ui.pickers.KvColorPicker
import java.util.Locale

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
    color: Color
) {
    Row {
        // Display the current color in a Box with a MaterialTheme shape
        Box(
            modifier = Modifier
                .padding(12.dp)
                .height(75.dp)
                .width(75.dp)
                .background(color, shape = MaterialTheme.shapes.large)
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
        )
    }
}
