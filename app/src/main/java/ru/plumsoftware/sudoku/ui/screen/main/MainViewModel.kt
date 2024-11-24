package ru.plumsoftware.sudoku.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.plumsoftware.sudoku.ui.screen.main.model.Effect
import ru.plumsoftware.sudoku.ui.screen.main.model.Event
import ru.plumsoftware.sudoku.ui.screen.main.model.State

class MainViewModel : ViewModel() {
    val state = State()
    val effect = MutableSharedFlow<Effect>()

    fun onEvent(event: Event) {
        when (event) {
            is Event.MainMenuButtonClick -> {
                viewModelScope.launch {
                    effect.emit(Effect.Navigate(route = event.mainMenuModel.route))
                }
            }
        }
    }

}