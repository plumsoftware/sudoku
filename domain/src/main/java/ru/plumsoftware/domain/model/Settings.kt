package ru.plumsoftware.domain.model

data class Settings(
    val isDarkTheme: Boolean = false,
    val bestTime: Long = 0,
    val differanceBestTime: Long = -1,
    val averageError: Double = 0.0,
    val oftenSudokuDifficulty: SudokuDifficulty = SudokuDifficulty.None
)