package ru.plumsoftware.sudoku.ui.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.plumsoftware.core.repository.sudoku.SudokuRepository
import ru.plumsoftware.sudoku.ui.screen.game.model.Event
import ru.plumsoftware.sudoku.ui.screen.game.model.State

class GameViewModel(
    private val sudokuRepository: SudokuRepository
) : ViewModel() {
    val state = MutableStateFlow(State())

    fun onEvent(event: Event) {
        when (event) {
            is Event.InitSudokuField -> {
                viewModelScope.launch(Dispatchers.Main) {
                    state.update {
                        it.copy(
                            sudokuMatrix = sudokuRepository.generateSudokuField(
                                sudokuDifficulty = event.sudokuDifficulty,
                                sudokuAreaSize = event.sudokuAreaSize
                            )
                        )
                    }
                }
            }

            is Event.ChangeSelectedMatrixItem -> {
                state.update {
                    it.copy(
                        selectedRow = event.row,
                        selectedColumn = event.column,
                        selectedGrid = event.gridCell
                    )
                }

                if (state.value.selectedNumber != -1) {
                    state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn] =
                        state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn].copy(
                            userNumber = state.value.selectedNumber,
                            isVisible = true
                        )

                    state.update {
                        it.copy(
                            sudokuMatrix = state.value.sudokuMatrix,
                            selectedNumber = -1,
                            selectedRow = -1,
                            selectedColumn = -1,
                            selectedGrid = -1
                        )
                    }
                }
            }

            is Event.ChangeSelectedNumber -> {
                state.update {
                    it.copy(
                        selectedNumber = event.number
                    )
                }

                if (state.value.selectedRow != -1 && state.value.selectedColumn != -1) {
                    state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn] =
                        state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn].copy(
                            userNumber = state.value.selectedNumber,
                            isVisible = true
                        )

                    state.update {
                        it.copy(
                            sudokuMatrix = state.value.sudokuMatrix,
                            selectedNumber = -1,
                            selectedRow = -1,
                            selectedColumn = -1,
                            selectedGrid = -1
                        )
                    }
                }
            }
        }
    }
}