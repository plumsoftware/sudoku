package ru.plumsoftware.sudoku.ui.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.theme.MainMenuButtonColorFamily

sealed class MainMenuModel(
    val icon: ImageVector,
    val colorFamily: MainMenuButtonColorFamily,
    @StringRes val title: Int
) {
    data object StartGame : MainMenuModel(
        icon = Icons.Outlined.PlayArrow,
        colorFamily = MainMenuButtonColorFamily.StartGame,
        title = R.string.main_menu_button_start_game
    )
}
