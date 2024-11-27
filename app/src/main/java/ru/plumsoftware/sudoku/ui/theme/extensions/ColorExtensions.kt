package ru.plumsoftware.sudoku.ui.theme.extensions

import androidx.compose.ui.graphics.Color

fun Color.disabled(): Color = this.copy(alpha = 0.5f)