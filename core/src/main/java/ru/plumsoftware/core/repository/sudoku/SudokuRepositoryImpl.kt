package ru.plumsoftware.core.repository.sudoku

import more.math.matrix.model.Matrix
import more.math.matrixOf
import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty
import ru.plumsoftware.domain.model.SudokuItem
import kotlin.math.sqrt
import kotlin.random.Random

class SudokuRepositoryImpl : SudokuRepository {
    override fun generateSudokuField(
        sudokuDifficulty: SudokuDifficulty,
        sudokuAreaSize: SudokuAreaSize
    ): Matrix<SudokuItem> {
        val size = sudokuAreaSize.number

        val chanceOfVisibleItem = when (sudokuDifficulty) {
            is SudokuDifficulty.Easy -> sudokuDifficulty.numberChance //80
            is SudokuDifficulty.ExtraHard -> sudokuDifficulty.numberChance //60
            is SudokuDifficulty.Hard -> sudokuDifficulty.numberChance //40
            is SudokuDifficulty.Medium -> sudokuDifficulty.numberChance //20
            SudokuDifficulty.None -> 100
        }

        val rows: MutableList<List<SudokuItem>> = mutableListOf()
        for (i in 0 until size) {
            //In row
            val inRow: MutableList<SudokuItem> = mutableListOf()
            for (j in 0 until size) {
                val rand = Random.nextInt(100)
                inRow.add(
                    SudokuItem(
                        isVisible = rand < chanceOfVisibleItem,
                        number = -1,
                        isCorrect = null
                    )
                )
            }
            rows.add(inRow)
        }

        val sudokuMatrix = matrixOf<SudokuItem>(*rows.map { it.toMutableList() }.toTypedArray())

        generateSudoku(sudokuMatrix, size)

        return sudokuMatrix
    }

    private fun isSafeToPlace(matrix: Matrix<SudokuItem>, row: Int, col: Int, num: Int, size: Int): Boolean {

        for (x in 0 until size) {
            if (matrix[row, x].number == num) {
                return false
            }
        }

        for (x in 0 until size) {
            if (matrix[x, col].number == num) {
                return false
            }
        }

        val sqrt = sqrt(size.toDouble()).toInt()
        val boxRowStart = row - row % sqrt
        val boxColStart = col - col % sqrt

        for (i in 0 until sqrt) {
            for (j in 0 until sqrt) {
                if (matrix[i + boxRowStart, j + boxColStart].number == num) {
                    return false
                }
            }
        }

        return true
    }

    private fun generateSudoku(matrix: Matrix<SudokuItem>, size: Int): Boolean {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (matrix[row, col].number != -1) continue

                val numbers = (1..size).shuffled()
                for (num in numbers) {
                    if (isSafeToPlace(matrix, row, col, num, size)) {
                        matrix[row, col] = SudokuItem(isVisible = matrix[row, col].isVisible, number = num, isCorrect = true)

                        if (generateSudoku(matrix, size)) {
                            return true
                        }

                        matrix[row, col] = SudokuItem(isVisible = matrix[row, col].isVisible, number = -1, isCorrect = null)
                    }
                }
                return false
            }
        }
        return true
    }
}
