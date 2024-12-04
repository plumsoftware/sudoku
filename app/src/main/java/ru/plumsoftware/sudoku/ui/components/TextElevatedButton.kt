package ru.plumsoftware.sudoku.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.plumsoftware.sudoku.ui.extensions.coloredShadow
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Shadows

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun TextElevatedButton(
    modifier: Modifier = Modifier,
    containerColor: Color,
    onContainerColor: Color,
    shadowColor: Color,
    title: Int,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isTap by interactionSource.collectIsPressedAsState()
    val offsetY by animateDpAsState(
        targetValue = if (isTap) Shadows.elevationMediumHeight else Shadows.ZERO,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )
    val offsetShadowY by animateDpAsState(
        targetValue = if (isTap) Shadows.ZERO else Shadows.elevationMediumHeight,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )

    Button(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .offset(y = offsetY)
            .coloredShadow(
                color = shadowColor,
                borderRadius = 12.dp,
                offsetY = offsetShadowY,
            )
            .then(modifier),
        onClick = {
            onClick()
        },
        interactionSource = interactionSource,
        contentPadding = Padding.ZERO,
        enabled = true,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = onContainerColor
        )
    ){
        Text(
            modifier = Modifier
                .weight(1.0f)
                .padding(vertical = Padding.medium),
            text = stringResource(id = title),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = onContainerColor,
                fontWeight = FontWeight.Normal
            )
        )
    }
}