package ru.plumsoftware.sudoku.ui.screen.game_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.components.Life
import ru.plumsoftware.sudoku.ui.components.MainMenuButton
import ru.plumsoftware.sudoku.ui.components.Selectable
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.LandScapePreview
import ru.plumsoftware.sudoku.ui.model.MainMenuModel
import ru.plumsoftware.sudoku.ui.model.PortraitPreview
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.screen.game_settings.model.Effect
import ru.plumsoftware.sudoku.ui.screen.game_settings.model.Event
import ru.plumsoftware.sudoku.ui.screen.global.model.GlobalEvent
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSettings(navHostController: NavHostController, onGlobalEvent: (GlobalEvent) -> Unit) {

    val viewModel = viewModel<GameSettingsViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                Effect.Back -> {
                    navHostController.navigateUp()
                }

                Effect.Play -> {
                    onGlobalEvent(
                        GlobalEvent.SetupGameData(
                            sudokuDifficulty = state.value.selectedDifficulty,
                            sudokuAreaSize = state.value.selectedSize,
                            isLife = state.value.isLife
                        )
                    )
                    navHostController.navigate(route = Routing.PLAY_GAME)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.game_settings),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.navigateUp()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Padding.medium)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(space = Space.GameSettings.itemScape),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Selectable(
                        list = state.value.sudokuDifficulty,
                        title = R.string.game_difficulty,
                        onClick = {
                            viewModel.onEvent(Event.OnSelectItem(item = it))
                        }
                    )

                    Life(onClick = {
                        viewModel.onEvent(Event.OnLifeClick(it))
                    })
                }
            }

            item {
                MainMenuButton(
                    mainMenuModel = MainMenuModel.StartGame,
                    onClick = {
                        viewModel.onEvent(Event.Play)
                    }
                )
            }
        }
    }
}

@Composable
@DefaultPreview
@PortraitPreview
@LandScapePreview
private fun GameSettingsPreview() {
    GameSettings(rememberNavController(), {})
}