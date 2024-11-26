package ru.plumsoftware.sudoku.ui.components

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty
import ru.plumsoftware.sudoku.ui.extensions.coloredShadow
import ru.plumsoftware.sudoku.ui.theme.Padding
import ru.plumsoftware.sudoku.ui.theme.Shadows
import ru.plumsoftware.sudoku.ui.theme.Space

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun <R> Selectable(list: List<R>, @StringRes title: Int, onClick: (R) -> Unit) {

    var selected by remember { mutableIntStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }
    val isTap by interactionSource.collectIsPressedAsState()
    val offsetY by animateDpAsState(
        targetValue = if (isTap) Shadows.elevationMediumHeight else Shadows.ZERO,
        animationSpec = tween(durationMillis = 100),
        label = ""
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = Space.small,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = Space.large,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            list.forEachIndexed { index, item ->
                ElevatedButton(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1.0f)
                        .offset(y = if (selected == index) Shadows.ZERO else offsetY)
                        .coloredShadow(
                            color = MaterialTheme.colorScheme.onBackground,
                            borderRadius = 12.dp,
                            offsetY = if (selected == index) Shadows.ZERO else Shadows.elevationMediumHeight,
                        ),
                    enabled = true,
                    border = BorderStroke(
                        width = if (selected == index) 2.dp else 1.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    contentPadding = PaddingValues(
                        horizontal = Padding.medium,
                        vertical = Padding.large
                    ),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    onClick = {
                        selected = index
                        onClick(item)
                    }
                ) {
                    when (item) {
                        is SudokuAreaSize -> Text(
                            text = "${item.number}x${item.number}",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = if (selected == index) FontWeight.SemiBold else FontWeight.Normal)
                        )

                        is SudokuDifficulty -> Text(
                            text = "$item",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = if (selected == index) FontWeight.SemiBold else FontWeight.Normal)
                        )
                    }
                }
            }
        }
    }
}