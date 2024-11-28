package ru.plumsoftware.sudoku

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.sudoku.ui.dialog.exit.ExitAppDialog
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.model.ScreenAnim
import ru.plumsoftware.sudoku.ui.screen.game_settings.GameSettings
import ru.plumsoftware.sudoku.ui.screen.global.GlobalViewModel
import ru.plumsoftware.sudoku.ui.screen.main.MainScreen
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme
import ru.plumsoftware.sudoku.ui.theme.extensions.Blur

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SudokuTheme {
                val navController = rememberNavController()
                val globalViewModel = viewModel<GlobalViewModel>()
                val globalState = globalViewModel.globalState.collectAsState()

                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(Blur.default)
                    )
                    {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(
                                    radiusX = 10.dp,
                                    radiusY = 10.dp,
                                    edgeTreatment = BlurredEdgeTreatment(null)
                                ),
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight
                        )
                    }
                    NavHost(
                        navController = navController,
                        startDestination = Routing.MAIN,
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                tween(ScreenAnim.enterMediumAnim)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down,
                                tween(ScreenAnim.exitMediumAnim)
                            )
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                tween(ScreenAnim.enterMediumAnim)
                            )
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down,
                                tween(ScreenAnim.exitMediumAnim)
                            )
                        }
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
}
