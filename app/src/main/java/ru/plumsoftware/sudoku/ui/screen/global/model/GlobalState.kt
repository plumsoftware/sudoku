package ru.plumsoftware.sudoku.ui.screen.global.model

import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty

data class GlobalState(
    val sudokuDifficulty: SudokuDifficulty = SudokuDifficulty.Easy(),
    val sudokuAreaSize: SudokuAreaSize = SudokuAreaSize.Small,
    val isLife: Boolean = false
)
