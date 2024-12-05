package ru.plumsoftware.sudoku.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.plumsoftware.domain.repository.settings.SettingsRepository
import ru.plumsoftware.sudoku.ui.screen.settings.model.Effect
import ru.plumsoftware.sudoku.ui.screen.settings.model.Event
import ru.plumsoftware.sudoku.ui.screen.settings.model.State

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val state = MutableStateFlow(State())
    val effect = MutableSharedFlow<Effect>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val value = settingsRepository.getIsDarkTheme()

            viewModelScope.launch(Dispatchers.Main) {
                state.update {
                    it.copy(isDark = value)
                }
            }
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.Back -> {
                viewModelScope.launch(Dispatchers.Main) {
                    effect.emit(Effect.Back)
                }
            }

            is Event.ChangeTheme -> {
                viewModelScope.launch(Dispatchers.IO) {
                    settingsRepository.saveIsDarkTheme(event.value)
                }
                state.update {
                    it.copy(isDark = event.value)
                }
            }
        }
    }

}