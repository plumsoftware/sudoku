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
        containerColor = blueContainer,
        onContainerColor = onBlueContainer,
        leadingIconColor = Color.White,
        leadingContainerColor = blueLeadingContainer,
        shadowColor = blueShadow
    )

    data object Setting : MainMenuButtonColorFamily(
        containerColor = blueContainer,
        onContainerColor = onBlueContainer,
        leadingIconColor = Color.White,
        leadingContainerColor = blueLeadingContainer,
        shadowColor = blueShadow
    )

    data object Yellow : MainMenuButtonColorFamily(
        containerColor = yellowContainer,
        onContainerColor = onYellowContainer,
        leadingIconColor = Color.White,
        leadingContainerColor = yellowLeadingContainer,
        shadowColor = yellowShadow
    )

    data object Exit : MainMenuButtonColorFamily(
        containerColor = blueContainer,
        onContainerColor = onBlueContainer,
        leadingIconColor = Color.White,
        leadingContainerColor = blueLeadingContainer,
        shadowColor = blueShadow
    )
}