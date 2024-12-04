package ru.plumsoftware.sudoku.ui.screen.game.model

sealed class Effect {
    data object Exit: Effect()
    data object Win: Effect()
    data object Pause: Effect()
}