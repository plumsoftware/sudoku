package ru.plumsoftware.sudoku.ui.screen.main.model

import androidx.compose.runtime.Immutable
import ru.plumsoftware.sudoku.ui.model.MainMenuModel

@Immutable
data class State(
    val mainMenuModelList: List<MainMenuModel> = listOf(
        MainMenuModel.StartGame,
        MainMenuModel.Settings,
        MainMenuModel.Exit
    )
)
