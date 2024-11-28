package ru.plumsoftware.sudoku.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.plumsoftware.sudoku.ui.extensions.coloredShadow
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.MainMenuModel
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Shadows
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun MainMenuButton(
    modifier: Modifier = Modifier,
    mainMenuModel: MainMenuModel,
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
                color = mainMenuModel.colorFamily.shadowColor,
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
            containerColor = mainMenuModel.colorFamily.containerColor,
            contentColor = mainMenuModel.colorFamily.onContainerColor
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(mainMenuModel.colorFamily.leadingContainerColor)
                    .size(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = mainMenuModel.icon,
                    contentDescription = stringResource(id = mainMenuModel.title)
                )
            }
            Text(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(vertical = Padding.medium),
                text = stringResource(id = mainMenuModel.title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = mainMenuModel.colorFamily.onContainerColor,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@DefaultPreview
private fun MainMenuButtonPreview() {
    SudokuTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            MainMenuButton(
                mainMenuModel = MainMenuModel.StartGame,
                onClick = {}
            )
        }
    }
}