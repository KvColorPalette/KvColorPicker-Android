package com.kavi.droid.color.picker.ui.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kavi.droid.color.palette.extension.hsl
import com.kavi.droid.color.picker.R
import com.kavi.droid.color.picker.ui.common.SliderHue

@Composable
fun BlendColorPicker(
    modifier: Modifier = Modifier,
    lastSelectedColor: Color = Color.White,
    onColorSelected: (selectedColor: Color) -> Unit
) {
    // State variables for HSL-A values
    val firstHue = remember { mutableFloatStateOf(lastSelectedColor.hsl.hue) }
    val secondHue = remember { mutableFloatStateOf(lastSelectedColor.hsl.hue) }

    val firstBlendColor = remember { mutableStateOf(lastSelectedColor) }
    val secondBlendColor = remember { mutableStateOf(lastSelectedColor) }

    var colorBias by remember { mutableFloatStateOf(.5f) }

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
                text = stringResource(R.string.phrase_select_color_blend),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 12.dp),
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.weight(.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SliderHue(Modifier.padding(top = 4.dp, bottom = 4.dp), onColorSelect = { selectedColor ->
                        firstHue.floatValue = selectedColor.hsl.hue
                        firstBlendColor.value = selectedColor
                    })
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.weight(.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SliderHue(Modifier.padding(top = 4.dp, bottom = 4.dp), onColorSelect = { selectedColor ->
                        secondHue.floatValue = selectedColor.hsl.hue
                        secondBlendColor.value = selectedColor
                    })
                }
            }

            Row (
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .weight(.2f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(50.dp)
                            .width(50.dp)
                            .background(
                                firstBlendColor.value,
                                shape = MaterialTheme.shapes.large
                            )
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(12.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Row (
                    modifier = Modifier.weight(.7f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_left_arrow),
                        contentDescription = "Left arrow",
                        Modifier
                            .weight(.25f)
                            .size(40.dp)
                    )

                    Slider(
                        modifier = Modifier
                            .height(50.dp)
                            .weight(.7f),
                        value = colorBias,
                        valueRange = 0f..1f,
                        onValueChange = {
                            colorBias = it
                        },
                    )
                    Icon(
                        painter = painterResource(R.drawable.icon_right_arrow),
                        contentDescription = "Right arrow",
                        Modifier
                            .weight(.25f)
                            .size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    modifier = Modifier
                        .weight(.2f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(50.dp)
                            .width(50.dp)
                            .background(
                                secondBlendColor.value,
                                shape = MaterialTheme.shapes.large
                            )
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(12.dp)
                            )
                    )
                }
            }
        }
    }
}