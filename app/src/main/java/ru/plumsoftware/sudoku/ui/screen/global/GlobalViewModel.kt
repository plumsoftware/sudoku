package ru.plumsoftware.sudoku.ui.screen.global

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.plumsoftware.sudoku.ui.screen.global.model.GlobalEvent
import ru.plumsoftware.sudoku.ui.screen.global.model.GlobalState

class GlobalViewModel : ViewModel() {
    val globalState = MutableStateFlow(GlobalState())

    fun onEvent(globalEvent: GlobalEvent) {
        when (globalEvent) {
            is GlobalEvent.SetupGameData -> {
                globalState.update {
                    it.copy(
                        sudokuDifficulty = globalEvent.sudokuDifficulty,
                        sudokuAreaSize = globalEvent.sudokuAreaSize,
                        isLife = globalEvent.isLife
                    )
                }
            }
        }
    }
}