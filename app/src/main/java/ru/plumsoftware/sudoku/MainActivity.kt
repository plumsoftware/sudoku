package ru.plumsoftware.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.sudoku.ui.dialog.exit.ExitAppDialog
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.screen.game_settings.GameSettings
import ru.plumsoftware.sudoku.ui.screen.global.GlobalViewModel
import ru.plumsoftware.sudoku.ui.screen.main.MainScreen
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SudokuTheme {
                val navController = rememberNavController()
                val globalViewModel = viewModel<GlobalViewModel>()
                val globalState = globalViewModel.globalState.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = Routing.MAIN
                ) {
                    composable(route = Routing.MAIN) {
                        MainScreen(navHostController = navController)
                    }
                    composable(route = Routing.START_GAME) {
                        GameSettings(
                            navHostController = navController,
                            onGlobalEvent = globalViewModel::onEvent
                        )
                    }
                    composable(route = Routing.SETTINGS) {

                    }
                    composable(route = Routing.PLAY_GAME) {

                    }
                    dialog(route = Routing.Dialog.EXIT) {
                        ExitAppDialog(navHostController = navController)
                    }
                }
            }
        }
    }
}
