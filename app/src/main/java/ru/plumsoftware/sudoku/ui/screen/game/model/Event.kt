package ru.plumsoftware.sudoku.ui.screen.game.model

import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty

sealed class Event {
    data class InitSudokuField(
        val sudokuDifficulty: SudokuDifficulty,
        val sudokuAreaSize: SudokuAreaSize
    ) : Event()

    data class ChangeSelectedMatrixItem(
        val row: Int,
        val column: Int,
        val gridCell: Int
    ) : Event()

    data class ChangeSelectedNumber(
        val number: Int
    ) : Event()
}