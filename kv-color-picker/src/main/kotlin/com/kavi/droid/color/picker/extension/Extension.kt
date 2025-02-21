package com.kavi.droid.color.picker.extension

/**
 * Converts a float value in the range [0, 1] to an integer color component in the range [0, 255].
 *
 * @return The integer representation of the color component.
 */
internal fun Float.toColorRangeInt(): Int = (this * 255 + 0.5f).toInt()

/**
 * Converts a float value in the range [0, 360] to an integer color component in the range [0, 360].
 *
 * @return The integer representation of the color component.
 */
internal fun Float.toHueRangeInt(): Int = (this * 360 + 0.5f).toInt()

/**
 * Converts a float value in the range [0, 100] to an integer color component in the range [0, 100].
 *
 * @return The integer representation of the color component.
 */
internal fun Float.toSaturationAndLightnessRangeInt(): Int = (this * 100 + 0.5f).toInt()