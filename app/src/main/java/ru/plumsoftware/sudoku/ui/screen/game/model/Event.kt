package ru.plumsoftware.sudoku.ui.screen.game.model

import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty
import ru.plumsoftware.domain.model.SudokuItem

sealed class Event {
    data class InitSudokuField(
        val sudokuDifficulty: SudokuDifficulty,
        val sudokuAreaSize: SudokuAreaSize
    ) : Event()

    data class ChangeSelectedMatrixItem(
        val item: SudokuItem
    ) : Event()
}