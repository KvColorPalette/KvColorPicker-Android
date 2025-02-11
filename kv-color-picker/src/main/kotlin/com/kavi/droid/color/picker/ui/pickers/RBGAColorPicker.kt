package com.kavi.droid.color.picker.ui.pickers

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
import androidx.compose.ui.unit.sp
import com.kavi.droid.color.palette.util.ColorUtil
import com.kavi.droid.color.picker.ui.common.AlphaSlider
import com.kavi.droid.color.picker.ui.common.ColorSlider
import com.kavi.droid.color.picker.ui.common.SelectedColorDetail

@Composable
fun KvColorPicker(modifier: Modifier = Modifier, onColorSelected: (selectedColor: Color) -> Unit) {
    // State variables for RGB-A values
    val red = rememberSaveable { mutableFloatStateOf(0f) }
    val green = rememberSaveable { mutableFloatStateOf(0f) }
    val blue = rememberSaveable { mutableFloatStateOf(0f) }
    val alpha = rememberSaveable { mutableFloatStateOf(1f) }

    var colorHex by remember { mutableStateOf(TextFieldValue("")) }
    var hasTextFieldFocus by remember { mutableStateOf(false) }

    // Retrieve a ClipboardManager object
    val clipboardManager = LocalClipboardManager.current

    // Derived state for the color based on RGBA values
    val color by remember {
        derivedStateOf {
            Color(red.floatValue, green.floatValue, blue.floatValue, alpha.floatValue)
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
        Text(
            text ="By dragging \'RED\', \'GREEN\', and \'BLUE\' bars below, you can select " +
                    "or generate your color you want exactly, or type your color\'s hex and set it.",
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

        SelectedColorDetail(color = color)

//        Row (
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            OutlinedTextField(
//                modifier = Modifier
//                    .padding(1.dp)
//                    .weight(1f)
//                    .onFocusChanged {
//                        hasTextFieldFocus = it.isFocused
//                    },
//                value = colorHex,
//                maxLines = 1,
//                label = { Text(text = "Color Hex") },
//                onValueChange = { newColorHex ->
//                    colorHex = newColorHex
//                }
//            )
//
//            Button(
//                modifier = Modifier
//                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
//                    .height(50.dp)
//                    .weight(1f),
//                    shape = RoundedCornerShape(8.dp),
//                    onClick = {
//                        if (hasTextFieldFocus) {
//                            val setColor = ColorUtil.getColorFromHex(colorHex.text)
//                            red.floatValue = setColor.red
//                            green.floatValue = setColor.green
//                            blue.floatValue = setColor.blue
//                        } else {
//                            clipboardManager.setText(colorHex.annotatedString)
//                        }
//                    }
//            ) {
//                Text(text = if(hasTextFieldFocus) "Set" else "Copy")
//            }
//        }
    }
}



@Preview(showBackground = true)
@Composable
fun ColorPickerUIPreview() {
    KvColorPicker(onColorSelected = {})
}