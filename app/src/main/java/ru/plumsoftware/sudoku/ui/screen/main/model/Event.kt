package ru.plumsoftware.sudoku.ui.screen.main.model

import ru.plumsoftware.sudoku.ui.model.MainMenuModel

sealed class Event {
    data class MainMenuButtonClick(val mainMenuModel: MainMenuModel) : Event()
}