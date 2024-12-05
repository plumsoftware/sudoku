package ru.plumsoftware.sudoku.ui.screen.game.model

sealed class Effect {
    data object Win: Effect()
}