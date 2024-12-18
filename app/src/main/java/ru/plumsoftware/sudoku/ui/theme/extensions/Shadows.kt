package ru.plumsoftware.sudoku.ui.theme.extensions

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object Shadows {
    val ZERO = 0.dp
    val elevationMediumHeight = 4.dp
    val mainMenuButtonElevation: ButtonElevation
        @Composable
        get() {
            return ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 10.dp,
                focusedElevation = 10.dp
            )
        }

    val textShadow = 8.0f
}