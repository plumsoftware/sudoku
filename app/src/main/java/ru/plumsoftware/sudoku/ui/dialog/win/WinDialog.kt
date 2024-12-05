package ru.plumsoftware.sudoku.ui.dialog.win

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import ru.plumsoftware.sudoku.ui.model.LandScapePreview
import ru.plumsoftware.sudoku.ui.model.PortraitPreview
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space
import ru.plumsoftware.sudoku.ui.theme.extensions.disabled

@Composable
fun WinDialog(navHostController: NavHostController) {

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
        Image(
            modifier = Modifier
                .scale(scaleX = 0.8f, scaleY = 0.8f),
            painter = painterResource(id = R.drawable.golden_cup),
            contentDescription = stringResource(id = R.string.win_title)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = Space.small,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.win_title),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.win_subtitle),
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground.disabled())
            )
        }
        TextButton(
            modifier = Modifier
                .wrapContentSize(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            onClick = {
                val adRequestConfiguration: AdRequestConfiguration = AdRequestConfiguration.Builder(
                    BuildConfig.interstitialAd).build() //RuStore
                mInterstitialAdLoader.loadAd(adRequestConfiguration)
            },
            contentPadding = PaddingValues(horizontal = Padding.large, vertical = Padding.small)
        ) {
            Text(
                text = stringResource(id = R.string.in_menu),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@DefaultPreview
@LandScapePreview
@PortraitPreview
private fun WinDialogPreview() {
    SudokuTheme {
        Scaffold {
            WinDialog(navHostController = rememberNavController())
        }
    }
}