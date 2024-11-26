package ru.plumsoftware.sudoku.ui.screen.game_settings.model

sealed class Event {
    data class OnSelectItem<R>(val item: R) : Event()
    data class OnLifeClick(val isSelected: Boolean) : Event()
    data object Back : Event()
    data object Play : Event()
}