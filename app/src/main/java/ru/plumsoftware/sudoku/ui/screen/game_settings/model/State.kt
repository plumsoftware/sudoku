package ru.plumsoftware.sudoku.ui.screen.game_settings.model

import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty

data class State(
    val sudokuAreaSize: List<SudokuAreaSize> = listOf(
        SudokuAreaSize.Small,
        SudokuAreaSize.Medium,
        SudokuAreaSize.Hard,
    ),
    val sudokuDifficulty: List<SudokuDifficulty> = listOf(
        SudokuDifficulty.Easy(),
        SudokuDifficulty.Medium(),
        SudokuDifficulty.Hard()
    ),
    val selectedDifficulty: SudokuDifficulty = sudokuDifficulty[0],
    val selectedSize: SudokuAreaSize = sudokuAreaSize[2],
    val isLife: Boolean = false
)
