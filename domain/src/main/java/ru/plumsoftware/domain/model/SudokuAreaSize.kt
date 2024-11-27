package ru.plumsoftware.domain.model

sealed class SudokuAreaSize(val number: Int) {
    data object None: SudokuAreaSize(number = 0)
    data object Small : SudokuAreaSize(number = 6)
    data object Medium : SudokuAreaSize(number = 8)
    data object Hard : SudokuAreaSize(number = 9)
}