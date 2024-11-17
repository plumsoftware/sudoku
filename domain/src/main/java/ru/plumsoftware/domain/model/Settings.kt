package ru.plumsoftware.domain.model

data class Settings(
    val isDarkTheme: Boolean = false,
    val bestTime: Long = 0,
    val differanceBestTime: Long = -1,
    val averageError: Double = 0.0,
    val oftenSudokuDifficulty: SudokuDifficulty = SudokuDifficulty.None
) {
    companion object {
        const val SETTINGS = "settings"
        const val IS_DARK_THEME = "isDarkTheme"
        const val BEST_TIME = "bestTime"
        const val DIFFERANCE_BEST_TIME = "differanceBestTime"
        const val AVERAGE_ERROR = "averageError"
        const val OFTEN_SUDOKU_DIFFICULTY = "oftenSudokuDifficulty"
    }
}