package com.kavi.droid.color.picker.ui.common

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toRect
import android.graphics.Color as AndroidColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SliderHue(modifier: Modifier, onColorSelect: (color: Color) -> Unit) {
    val hueValueState = rememberSaveable { mutableFloatStateOf(0f) }
    val huePanel = rememberSaveable { mutableStateOf(RectF()) }
    val hueColors = rememberSaveable { mutableStateOf(IntArray(0)) }

    val hsv = remember {
        val hsv = floatArrayOf(0f, 0f, 0f)
        AndroidColor.colorToHSV(Color.Blue.toArgb(), hsv)

        mutableStateOf(
            Triple(hsv[0], hsv[1], hsv[2])
        )
    }

    // Launch an effect to invoke the provided callback with the selected color
    LaunchedEffect(hueValueState.floatValue) {
        val selectedHue = pointToHue(hueValueState.floatValue, huePanel.value)
        hsv.value = Triple(selectedHue, hsv.value.second, hsv.value.third)

        val generatedColor = Color.hsv(hsv.value.first, hsv.value.second, hsv.value.third)
        onColorSelect.invoke(generatedColor)
    }

    Box (modifier = modifier
        .fillMaxWidth()
    ) {
        HuePanel(hueColors, huePanel)

        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            valueRange = 0f..(hueColors.value.size).toFloat(),
            value = hueValueState.floatValue,
            onValueChange = hueValueState.component2(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent
            ),
            thumb = {
                Box(
                    modifier = Modifier
                        .height(45.dp)
                        .width(10.dp)
                        .background(Color.Transparent, shape = MaterialTheme.shapes.large)
                        .border(
                            2.dp,
                            Color.Gray,
                            RoundedCornerShape(12.dp)
                        )
                )
            }
        )
    }
}

@Composable
private fun HuePanel(hueColors: MutableState<IntArray>, huePanel: MutableState<RectF>) {
    Canvas(
        modifier = Modifier
            .padding(top = 1.dp)
            .height(45.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12))
    ) {
        val bitmap = Bitmap.createBitmap(size.width.toInt(), size.height.toInt(), Bitmap.Config.ARGB_8888)
        val hueCanvas = android.graphics.Canvas(bitmap)
        huePanel.value = RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat())
        hueColors.value = IntArray((huePanel.value.width()).toInt())
        var hue = 0f
        for (i in hueColors.value.indices) {
            hueColors.value[i] = AndroidColor.HSVToColor(floatArrayOf(hue, 1f, 1f))
            hue += 360f / hueColors.value.size
        }
        val linePaint = Paint()
        linePaint.strokeWidth = 0f
        for(t in hueColors.value.indices) {
            linePaint.color = hueColors.value[t]
            hueCanvas.drawLine(t.toFloat(), 0f, t.toFloat(), huePanel.value.bottom, linePaint)
        }

        drawBitmap(bitmap = bitmap, panel = huePanel.value)
    }
}

private fun pointToHue(pointX: Float, huePanel: RectF): Float {
    val width = huePanel.width()
    val x = when {
        pointX < huePanel.left -> 0f
        pointX > huePanel.right -> width
        else -> pointX - huePanel.left
    }
    return x * 360 / width
}

private fun DrawScope.drawBitmap(
    bitmap: Bitmap,
    panel: RectF
) {
    drawIntoCanvas {
        it.nativeCanvas.drawBitmap(
            bitmap,
            null,
            panel.toRect(),
            null
        )
    }
}

@Preview
@Composable
fun HuePanel_Preview() {
    SliderHue(Modifier, {})
}