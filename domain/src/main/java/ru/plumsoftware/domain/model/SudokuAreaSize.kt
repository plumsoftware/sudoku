package ru.plumsoftware.domain.model

sealed class SudokuAreaSize {
    data class Small(val number: Int = 2) : SudokuAreaSize()
    data class Medium(val number: Int = 3) : SudokuAreaSize()
    data class Hard(val number: Int = 4) : SudokuAreaSize()
}