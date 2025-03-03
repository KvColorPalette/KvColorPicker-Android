package com.kavi.droid.color.picker.extension

/**
 * Converts a float value in the range [0, 1] to an integer color component in the range [0, 255].
 *
 * @return The integer representation of the color component.
 */
internal fun Float.toColorRangeInt(): Int = (this * 255 + 0.5f).toInt()