package ru.plumsoftware.domain.model

sealed class SudokuAreaSize {
    data class Small(val number: Int = 6) : SudokuAreaSize()
    data class Medium(val number: Int = 8) : SudokuAreaSize()
    data class Hard(val number: Int = 10) : SudokuAreaSize()
}