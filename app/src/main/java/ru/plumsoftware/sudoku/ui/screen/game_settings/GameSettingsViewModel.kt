package ru.plumsoftware.sudoku.ui.screen.game_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.plumsoftware.domain.model.SudokuAreaSize
import ru.plumsoftware.domain.model.SudokuDifficulty
import ru.plumsoftware.sudoku.ui.screen.game_settings.model.Effect
import ru.plumsoftware.sudoku.ui.screen.game_settings.model.Event
import ru.plumsoftware.sudoku.ui.screen.game_settings.model.State

class GameSettingsViewModel : ViewModel() {
    val state = MutableStateFlow(
        State()
    )
    val effect = MutableSharedFlow<Effect>()

    fun onEvent(event: Event) {
        when (event) {
            Event.Back -> {
                viewModelScope.launch {
                    effect.emit(Effect.Back)
                }
            }

            is Event.OnSelectItem<*> -> {
                when (val selected = event.item) {
                    is SudokuAreaSize -> {
                        viewModelScope.launch(Dispatchers.Main) {
                            state.update {
                                it.copy(selectedSize = selected)
                            }
                        }
                    }
                    is SudokuDifficulty -> {
                        viewModelScope.launch(Dispatchers.Main) {
                            state.update {
                                it.copy(selectedDifficulty = selected)
                            }
                        }
                    }
                }
            }

            Event.Play -> {
                viewModelScope.launch(Dispatchers.Main) {
                    effect.emit(Effect.Play)
                }
            }

            is Event.OnLifeClick -> {
                viewModelScope.launch(Dispatchers.Main) {
                    state.update {
                        it.copy(isLife = event.isSelected)
                    }
                }
            }
        }
    }
}