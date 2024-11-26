package ru.plumsoftware.sudoku.ui.screen.global.model

import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty

sealed class GlobalEvent {
    data class SetupGameData(
        val sudokuAreaSize: SudokuAreaSize,
        val sudokuDifficulty: SudokuDifficulty,
        val isLife: Boolean
    ) : GlobalEvent()
}