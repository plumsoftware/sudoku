package ru.plumsoftware.domain.repository.settings

import ru.plumsoftware.domain.model.SudokuDifficulty

interface SettingsRepository {
    suspend fun saveIsDarkTheme(isDarkTheme: Boolean)
    suspend fun saveBestTime(bestTime: Long)
    suspend fun saveDifferanceBestTime(differanceBestTime: Long)
    suspend fun saveAverageError(averageError: Double)
    suspend fun saveOftenSudokuDifficulty(oftenSudokuDifficulty: SudokuDifficulty)

    suspend fun getIsDarkTheme() : Boolean
    suspend fun getBestTime() : Long
    suspend fun getDifferanceBestTime() : Long
    suspend fun getAverageError() : Double
    suspend fun getOftenSudokuDifficulty() : SudokuDifficulty
}