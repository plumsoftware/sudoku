package ru.plumsoftware.sudoku.ui.screen.settings.model

sealed class Event {
    data class ChangeTheme(val value: Boolean) : Event()
    data object Back: Event()
}