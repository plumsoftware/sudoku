package ru.plumsoftware.sudoku.ui.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import ru.plumsoftware.sudoku.ui.theme.extensions.Blur
import ru.plumsoftware.sudoku.ui.theme.extensions.Shadows

fun TextStyle.textShadow() = this.copy(
    shadow = Shadow(
        color = Color.Black,
        offset = Offset(x = 0f, y = Shadows.textShadow),
        blurRadius = Blur.textShadowBlur
    )
)