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

    override suspend fun saveIsDarkTheme(isDarkTheme: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun saveBestTime(bestTime: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun saveDifferanceBestTime(differanceBestTime: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAverageError(averageError: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun saveOftenSudokuDifficulty(oftenSudokuDifficulty: SudokuDifficulty) {
        TODO("Not yet implemented")
    }

    override suspend fun getIsDarkTheme(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getBestTime(): Long {
        TODO("Not yet implemented")
    }

    override suspend fun getDifferanceBestTime(): Long {
        TODO("Not yet implemented")
    }

    override suspend fun getAverageError(): Double {
        TODO("Not yet implemented")
    }

    override suspend fun getOftenSudokuDifficulty(): SudokuDifficulty {
        TODO("Not yet implemented")
    }
}