package ru.plumsoftware.sudoku.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.theme.extensions.Blur
import ru.plumsoftware.sudoku.ui.theme.extensions.Shadows

@Composable
fun BorderedText(
    text: String = stringResource(id = R.string.app_title),
    borderColor: Color = Color.Black,
    textColor: Color = Color.White,
    fontSize: TextUnit = 64.sp
) {
    Text(
        text = text,
        color = textColor,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge.copy(
            letterSpacing = 0.1.sp,
            shadow = Shadow(
                color = borderColor,
                offset = Offset(x = 0f, y = Shadows.textShadow),
                blurRadius = Blur.textShadowBlur
            ),
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Black,
        )
    )
}
