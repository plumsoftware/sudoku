package ru.plumsoftware.sudoku.ui.screen.game_settings.model

sealed class Effect {
    data object Play : Effect()
    data object Back : Effect()
}