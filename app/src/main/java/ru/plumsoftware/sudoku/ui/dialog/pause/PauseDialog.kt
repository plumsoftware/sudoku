package ru.plumsoftware.sudoku.ui.dialog.pause

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yandex.mobile.ads.common.AdError
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoadListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoader
import ru.plumsoftware.sudoku.BuildConfig
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space


@Composable
fun PauseDialog(navHostController: NavHostController) {

    val context = LocalContext.current
    var mInterstitialAd: InterstitialAd? = null
    val mInterstitialAdLoader = InterstitialAdLoader(context)

    mInterstitialAdLoader.setAdLoadListener(object : InterstitialAdLoadListener {
        override fun onAdLoaded(interstitialAd: InterstitialAd) {
            mInterstitialAd = interstitialAd

            mInterstitialAd!!.setAdEventListener(object : InterstitialAdEventListener {
                override fun onAdShown() {
                }

                override fun onAdFailedToShow(adError: AdError) {
                    navHostController.navigate(route = Routing.MAIN)
                    navHostController.clearBackStack(route = Routing.PLAY_GAME)
                }

                override fun onAdDismissed() {
                    if (mInterstitialAd != null) {
                        mInterstitialAd!!.setAdEventListener(null)
                        mInterstitialAd = null
                    }
                    navHostController.navigate(route = Routing.MAIN)
                    navHostController.clearBackStack(route = Routing.PLAY_GAME)
                }

                override fun onAdClicked() {
                    navHostController.navigate(route = Routing.MAIN)
                    navHostController.clearBackStack(route = Routing.PLAY_GAME)
                }

                override fun onAdImpression(impressionData: ImpressionData?) {}
            })
            mInterstitialAd!!.show(context as Activity)
        }

        override fun onAdFailedToLoad(error: AdRequestError) {
            navHostController.navigate(route = Routing.MAIN)
            navHostController.clearBackStack(route = Routing.PLAY_GAME)
        }
    })

    Column(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize()
            .padding(horizontal = Padding.large, vertical = Padding.medium),
        verticalArrangement = Arrangement.spacedBy(
            space = Space.medium,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.pause),
            style = MaterialTheme.typography.titleLarge
        )

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = {
                navHostController.navigateUp()
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = Space.small,
                    alignment = Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = stringResource(R.string.main_menu_button_start_game)
                )

                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.back_in_game),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.error
            ),
            onClick = {
                val adRequestConfiguration: AdRequestConfiguration = AdRequestConfiguration.Builder(BuildConfig.interstitialAd).build() //RuStore
                mInterstitialAdLoader.loadAd(adRequestConfiguration)
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = Space.small,
                    alignment = Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.ExitToApp,
                    contentDescription = stringResource(R.string.in_menu)
                )

                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.in_menu),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@DefaultPreview
fun PauseDialogPreview() {
    Scaffold {
        PauseDialog(rememberNavController())
    }
}