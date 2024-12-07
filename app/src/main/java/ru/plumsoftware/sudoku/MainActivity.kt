package ru.plumsoftware.sudoku

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.yandex.mobile.ads.appopenad.AppOpenAd
import com.yandex.mobile.ads.appopenad.AppOpenAdEventListener
import com.yandex.mobile.ads.appopenad.AppOpenAdLoadListener
import com.yandex.mobile.ads.appopenad.AppOpenAdLoader
import com.yandex.mobile.ads.common.AdError
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.InitializationListener
import com.yandex.mobile.ads.common.MobileAds.initialize
import ru.plumsoftware.core.repository.settings.SettingsRepositoryImpl
import ru.plumsoftware.domain.model.Settings
import ru.plumsoftware.sudoku.ui.dialog.exit.ExitAppDialog
import ru.plumsoftware.sudoku.ui.dialog.pause.PauseDialog
import ru.plumsoftware.sudoku.ui.dialog.win.WinDialog
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.model.ScreenAnim
import ru.plumsoftware.sudoku.ui.screen.game.Game
import ru.plumsoftware.sudoku.ui.screen.game_settings.GameSettings
import ru.plumsoftware.sudoku.ui.screen.global.GlobalViewModel
import ru.plumsoftware.sudoku.ui.screen.main.MainScreen
import ru.plumsoftware.sudoku.ui.screen.settings.Settings
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize(this@MainActivity, InitializationListener {})

        val context: Context = this@MainActivity
        val activity: Activity = this@MainActivity

        enableEdgeToEdge()
        setContent {

            var isDark: Boolean by remember { mutableStateOf(Settings().isDarkTheme) }
            LaunchedEffect(key1 = Unit) {
                isDark = SettingsRepositoryImpl(context).getIsDarkTheme()
            }

            SudokuTheme(
                darkTheme = isDark
            ) {
                val navController = rememberNavController()
                val globalViewModel = viewModel<GlobalViewModel>()
                val globalState = globalViewModel.globalState.collectAsState()

                val appOpenAdLoader = AppOpenAdLoader(context)
                val adRequestConfiguration: AdRequestConfiguration =
                    AdRequestConfiguration.Builder(BuildConfig.appOpenAd).build()

                val appOpenAdEventListener: AppOpenAdEventListener = object : AppOpenAdEventListener {
                    override fun onAdShown() {}

                    override fun onAdFailedToShow(adError: AdError) {}

                    override fun onAdDismissed() {}

                    override fun onAdClicked() {}

                    override fun onAdImpression(impressionData: ImpressionData?) {}
                }

                val appOpenAdLoadListener: AppOpenAdLoadListener = object : AppOpenAdLoadListener {
                    override fun onAdLoaded(appOpenAd: AppOpenAd) {
                        appOpenAd.setAdEventListener(appOpenAdEventListener)
                        appOpenAd.show(activity)
                    }

                    override fun onAdFailedToLoad(error: AdRequestError) {
                    }
                }

                appOpenAdLoader.setAdLoadListener(appOpenAdLoadListener)
                appOpenAdLoader.loadAd(adRequestConfiguration)

                Scaffold {
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
                            Settings(navHostController = navController)
                        }
                        composable(route = Routing.PLAY_GAME) {
                            Game(
                                navHostController = navController,
                                globalState = globalState
                            )
                        }
                        dialog(route = Routing.Dialog.EXIT) {
                            ExitAppDialog(navHostController = navController)
                        }
                        dialog(route = Routing.Dialog.GAME_PAUSE) {
                            PauseDialog(navHostController = navController)
                        }
                        dialog(route = Routing.Dialog.GAME_WIN) {
                            WinDialog(navHostController = navController, activity = this@MainActivity)
                        }
                    }
                }
            }
        }
    }
}
