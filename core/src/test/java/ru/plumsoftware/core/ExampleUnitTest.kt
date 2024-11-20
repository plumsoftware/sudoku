package ru.plumsoftware.core

import org.junit.Test

import org.junit.Assert.*
import ru.plumsoftware.core.repository.sudoku.SudokuRepositoryImpl
import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty

class ExampleUnitTest {
    @Test
    fun matrix() {
        val sudokuRepository = SudokuRepositoryImpl()
        val e = sudokuRepository.generateSudokuField(
            sudokuAreaSize = SudokuAreaSize.Small(),
            sudokuDifficulty = SudokuDifficulty.Easy()
        )

        assertEquals(e.rows.size, 6)
    }
}