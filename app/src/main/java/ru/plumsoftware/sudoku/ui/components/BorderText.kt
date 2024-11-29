package ru.plumsoftware.sudoku.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.theme.extensions.Blur
import ru.plumsoftware.sudoku.ui.theme.extensions.Shadows

@ExperimentalComposeUiApi
@Composable
fun OutlinedText(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.app_title),
    style: TextStyle = MaterialTheme.typography.headlineLarge,
    fontSize: TextUnit = 64.sp,
    letterSpacing: TextUnit = 0.15.sp,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Black,
    strokeWidth: Float = 10.0f,
    shadowColor: Color = MaterialTheme.colorScheme.primaryContainer,
    strokeColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.semantics { invisibleToUser() },
            color = strokeColor,
            fontSize = fontSize,
            textAlign = textAlign,
            overflow = TextOverflow.Clip,
            softWrap = true,
            style = style.copy(
                letterSpacing = letterSpacing,
                fontWeight = fontWeight,
                drawStyle = Stroke(
                    width = strokeWidth
                )
            ),
        )

        Text(
            modifier = modifier,
            text = text,
            color = MaterialTheme.colorScheme.background,
            fontSize = fontSize,
            overflow = TextOverflow.Clip,
            softWrap = true,
            textAlign = textAlign,
            style = style.copy(
                letterSpacing = letterSpacing,
                fontWeight = fontWeight,
                shadow = Shadow(
                    color = shadowColor,
                    offset = Offset(x = 0f, y = Shadows.textShadow),
                    blurRadius = Blur.textShadowBlur
                ),
            ),
        )
    }
}
