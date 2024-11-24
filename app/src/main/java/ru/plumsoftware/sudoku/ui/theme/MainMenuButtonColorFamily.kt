package ru.plumsoftware.sudoku.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
sealed class MainMenuButtonColorFamily (
    val containerColor: Color = Color.Unspecified,
    val onContainerColor: Color = Color.Unspecified,
    val leadingIconColor: Color = Color.Unspecified,
    val leadingContainerColor: Color = Color.Unspecified,
    val shadowColor: Color = Color.Unspecified,
) {
    data object StartGame : MainMenuButtonColorFamily(
        containerColor = redContainer,
        onContainerColor = Color.White,
        leadingIconColor = Color.White,
        leadingContainerColor = redLeadingContainer,
        shadowColor = redShadow
    )
}