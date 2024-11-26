package ru.plumsoftware.sudoku.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.components.MainMenuButton
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.LandScapePreview
import ru.plumsoftware.sudoku.ui.model.PortraitPreview
import ru.plumsoftware.sudoku.ui.screen.main.model.Effect
import ru.plumsoftware.sudoku.ui.screen.main.model.Event
import ru.plumsoftware.sudoku.ui.theme.Padding
import ru.plumsoftware.sudoku.ui.theme.Space

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {
    val viewModel = viewModel<MainViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is Effect.Navigate -> {
                    navHostController.navigate(route = effect.route)
                }
            }
        }
    }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(all = Padding.medium),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )

            FlowRow(
                verticalArrangement = Arrangement.spacedBy(
                    space = Space.MainScreen.buttons,
                    alignment = Alignment.CenterVertically
                ),
                horizontalArrangement = Arrangement.Center
            ) {
                viewModel.state.mainMenuModelList.forEachIndexed { _, mainMenuModel ->
                    MainMenuButton(
                        mainMenuModel = mainMenuModel,
                        onClick = {
                            viewModel.onEvent(Event.MainMenuButtonClick(mainMenuModel = mainMenuModel))
                        }
                    )
                }
            }
        }
    }
}

@Composable
@DefaultPreview
@LandScapePreview
@PortraitPreview
private fun MainScreenPreview() {
    MainScreen(navHostController = rememberNavController())
}