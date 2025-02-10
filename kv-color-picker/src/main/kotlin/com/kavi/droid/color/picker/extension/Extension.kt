package com.kavi.droid.color.palette.app.extension

/**
 * Converts a float value in the range [0, 1] to an integer color component in the range [0, 255].
 *
 * @return The integer representation of the color component.
 */
fun Float.toColorInt(): Int = (this * 255 + 0.5f).toInt()