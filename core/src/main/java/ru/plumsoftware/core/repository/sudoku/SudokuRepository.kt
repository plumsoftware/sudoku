package ru.plumsoftware.core.repository.sudoku

import more.math.matrix.model.Matrix
import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty
import ru.plumsoftware.domain.model.SudokuItem

interface SudokuRepository {
    fun generateSudokuField(
        sudokuDifficulty: SudokuDifficulty,
        sudokuAreaSize: SudokuAreaSize
    ): Matrix<SudokuItem>
}