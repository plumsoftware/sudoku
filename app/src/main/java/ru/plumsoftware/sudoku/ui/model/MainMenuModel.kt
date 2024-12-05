package ru.plumsoftware.sudoku.ui.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.theme.MainMenuButtonColorFamily

sealed class MainMenuModel(
    val icon: ImageVector,
    val colorFamily: MainMenuButtonColorFamily,
    @StringRes val title: Int,
    val route: String
) {
    data object StartGame : MainMenuModel(
        icon = Icons.Rounded.PlayArrow,
        colorFamily = MainMenuButtonColorFamily.StartGame,
        title = R.string.main_menu_button_start_game,
        route = Routing.START_GAME
    )

    data object Settings : MainMenuModel(
        icon = Icons.Rounded.Settings,
        colorFamily = MainMenuButtonColorFamily.Setting,
        title = R.string.main_menu_button_settings,
        route = Routing.SETTINGS
    )

    data object Exit : MainMenuModel(
        icon = Icons.AutoMirrored.Rounded.ExitToApp,
        colorFamily = MainMenuButtonColorFamily.Exit,
        title = R.string.main_menu_button_exit,
        route = Routing.Dialog.EXIT
    )
}
