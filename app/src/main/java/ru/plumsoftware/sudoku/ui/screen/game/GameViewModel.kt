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
                        selectedColumn = event.column
                    )
                }
            }
        }
    }
}