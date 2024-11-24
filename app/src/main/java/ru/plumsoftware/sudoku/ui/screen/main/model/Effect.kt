package ru.plumsoftware.sudoku.ui.screen.main.model

sealed class Effect {
    data class Navigate(val route: String) : Effect()
}