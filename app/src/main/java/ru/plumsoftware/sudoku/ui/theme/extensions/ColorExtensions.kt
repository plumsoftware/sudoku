package ru.plumsoftware.sudoku.ui.theme.extensions

import androidx.compose.ui.graphics.Color

fun Color.disabled(value: Float = 0.5f): Color = this.copy(alpha = value)