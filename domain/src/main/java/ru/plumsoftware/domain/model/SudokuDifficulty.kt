package ru.plumsoftware.domain.model

sealed class SudokuDifficulty {
    data object None : SudokuDifficulty()
    data class Easy(val numberChance: Int = 80) : SudokuDifficulty()
    data class Medium(val numberChance: Int = 60) : SudokuDifficulty()
    data class Hard(val numberChance: Int = 40) : SudokuDifficulty()
    data class ExtraHard(val numberChance: Int = 20) : SudokuDifficulty()
}