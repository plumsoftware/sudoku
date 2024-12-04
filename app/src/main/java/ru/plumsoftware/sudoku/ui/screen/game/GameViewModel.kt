package ru.plumsoftware.sudoku.ui.screen.game

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.plumsoftware.core.repository.sudoku.SudokuRepository
import ru.plumsoftware.sudoku.ui.screen.game.model.Effect
import ru.plumsoftware.sudoku.ui.screen.game.model.Event
import ru.plumsoftware.sudoku.ui.screen.game.model.State
import kotlin.time.Duration.Companion.seconds

class GameViewModel(
    private val sudokuRepository: SudokuRepository
) : ViewModel() {
    val state = MutableStateFlow(State())
    val effect = MutableSharedFlow<Effect>()

    private val calendar: Calendar = Calendar.getInstance()

    init {
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
    }

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

                if (state.value.selectedNumber != -1 && state.value.selectedNumber != 10) {
                    val isError = checkMatrix()
                    state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn] =
                        state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn].copy(
                            userNumber = state.value.selectedNumber,
                            isVisible = true,
                            isCorrect = isError
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
                } else if (state.value.selectedNumber == 10) {
                    state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn] =
                        state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn].copy(
                            userNumber = -1,
                            isVisible = false,
                            isCorrect = true
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

                val isGameFinished = isGameFinished()

                state.update {
                    it.copy(
                        isGameFinished = isGameFinished
                    )
                }

                if (isGameFinished) {
                    viewModelScope.launch {
                        effect.emit(Effect.Win)
                    }
                }
            }

            is Event.ChangeSelectedNumber -> {
                state.update {
                    it.copy(
                        selectedNumber = event.number
                    )
                }

                if (state.value.selectedRow != -1 && state.value.selectedColumn != -1 && state.value.selectedNumber != 10) {
                    val isError = checkMatrix()
                    state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn] =
                        state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn].copy(
                            userNumber = state.value.selectedNumber,
                            isVisible = true,
                            isCorrect = isError
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
                } else if (state.value.selectedRow != -1 && state.value.selectedColumn != -1 && state.value.selectedNumber == 10) {
                    state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn] =
                        state.value.sudokuMatrix[state.value.selectedRow, state.value.selectedColumn].copy(
                            userNumber = -1,
                            isVisible = false,
                            isCorrect = true
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

                val isGameFinished = isGameFinished()

                state.update {
                    it.copy(
                        isGameFinished = isGameFinished
                    )
                }

                if (isGameFinished) {
                    viewModelScope.launch {
                        effect.emit(Effect.Win)
                    }
                }
            }

            Event.StartTime -> {
                viewModelScope.launch(Dispatchers.Main) {
                    time()
                }
            }
        }
    }

    private inline fun checkMatrix(): Boolean {
        val userNumber = state.value.selectedNumber

        for (col in 0..8) {
            val item = state.value.sudokuMatrix[state.value.selectedRow, col]

            if (item.userNumber == userNumber) {
                state.update { it.copy(errorsCount = (state.value.errorsCount + 1)) }
                return false
            }
        }

        for (row in 0..8) {
            val item = state.value.sudokuMatrix[row, state.value.selectedColumn]

            if (item.userNumber == userNumber) {
                state.update { it.copy(errorsCount = (state.value.errorsCount + 1)) }
                return false
            }
        }
        return true
    }

    private suspend fun time() {
        delay(1.seconds)

        calendar.add(Calendar.SECOND, 1)
        val time = calendar.timeInMillis
        state.update {
            it.copy(
                time = time
            )
        }

        time()
    }

    private inline fun isGameFinished(): Boolean {
        state.value.sudokuMatrix.rows.forEachIndexed { _, sudokuItems ->
            sudokuItems.forEachIndexed { _, sudokuItem ->
                if (sudokuItem.userNumber == -1 || !sudokuItem.isCorrect) return false
            }
        }
        return true
    }
}