package ru.plumsoftware.sudoku.ui.screen.game.model

import more.math.matrix.model.Matrix
import ru.plumsoftware.domain.model.SudokuItem

data class State(
    val sudokuMatrix: Matrix<SudokuItem> = Matrix(mutableListOf()),
    val selectedRow: Int = -1,
    val selectedColumn: Int = -1,
    val selectedItem: SudokuItem = SudokuItem.empty(),
    val selectedNumber: Int = -1,
    val selectedGrid: Int = -1,
    val errorsCount: Int = -1,
    val time: Long = 0L,
    val isGameFinished: Boolean = false
)
