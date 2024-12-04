package ru.plumsoftware.sudoku.ui.dialog.win

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.model.Routing
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space

@Composable
fun WinDialog(navHostController: NavHostController) {
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
        horizontalAlignment = Alignment.Start
    ) {
        Image(
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
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.win_title),
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.win_subtitle),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

    TextButton(
        modifier = Modifier.wrapContentSize(),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        onClick = {
            navHostController.navigate(route = Routing.MAIN)
            navHostController.clearBackStack(route = Routing.PLAY_GAME)
        }
    ) {
        Text(
            text = stringResource(id = R.string.in_menu),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}