package ru.plumsoftware.sudoku.ui.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object Shadows {

    val elevationMediumHeight = 6.dp
    val mainMenuButtonElevation: ButtonElevation
        @Composable
        get() {
            return ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp,
                focusedElevation = 2.dp
            )
        }
}