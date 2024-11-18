package ru.plumsoftware.core.repository.settings

import android.content.Context
import android.content.SharedPreferences
import ru.plumsoftware.domain.model.Settings
import ru.plumsoftware.domain.model.SudokuDifficulty
import ru.plumsoftware.domain.repository.settings.SettingsRepository

class SettingsRepositoryImpl constructor(context: Context) : SettingsRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Settings.SETTINGS, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    private val baseSettings: Settings = Settings()

    override suspend fun saveIsDarkTheme(isDarkTheme: Boolean) {
        editor.putBoolean(Settings.IS_DARK_THEME, isDarkTheme)
    }

    override suspend fun saveBestTime(bestTime: Long) {
        editor.putLong(Settings.BEST_TIME, bestTime)
    }

    override suspend fun saveDifferanceBestTime(differanceBestTime: Long) {
        editor.putLong(Settings.DIFFERANCE_BEST_TIME, differanceBestTime)
    }

    override suspend fun saveAverageError(averageError: Double) {
        editor.putFloat(Settings.AVERAGE_ERROR, averageError.toFloat())
    }

    override suspend fun saveOftenSudokuDifficulty(oftenSudokuDifficulty: SudokuDifficulty) {
        editor.putString(Settings.OFTEN_SUDOKU_DIFFICULTY, oftenSudokuDifficulty.toString())
    }

    override suspend fun getIsDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(Settings.IS_DARK_THEME, baseSettings.isDarkTheme)
    }

    override suspend fun getBestTime(): Long {
        return sharedPreferences.getLong(Settings.BEST_TIME, baseSettings.bestTime)
    }

    override suspend fun getDifferanceBestTime(): Long {
        return sharedPreferences.getLong(Settings.DIFFERANCE_BEST_TIME, baseSettings.differanceBestTime)
    }

    override suspend fun getAverageError(): Double {
        return sharedPreferences.getFloat(Settings.AVERAGE_ERROR, baseSettings.averageError.toFloat()).toDouble()
    }

    override suspend fun getOftenSudokuDifficulty(): SudokuDifficulty {
        return SudokuDifficulty.fromString(from = sharedPreferences.getString(Settings.OFTEN_SUDOKU_DIFFICULTY, SudokuDifficulty.None.toString())!!)
    }
}