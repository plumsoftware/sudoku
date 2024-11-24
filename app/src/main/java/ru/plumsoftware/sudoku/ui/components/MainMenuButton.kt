package ru.plumsoftware.sudoku.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.plumsoftware.sudoku.ui.extensions.coloredShadow
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.MainMenuModel
import ru.plumsoftware.sudoku.ui.theme.Padding
import ru.plumsoftware.sudoku.ui.theme.Shadows
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme

@Composable
fun MainMenuButton(
    modifier: Modifier = Modifier,
    mainMenuModel: MainMenuModel,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .coloredShadow(
                color = mainMenuModel.colorFamily.shadowColor,
                borderRadius = 12.dp,
                offsetY = Shadows.elevationMediumHeight,
            )
            .then(modifier),
        onClick = onClick,
        contentPadding = Padding.ZERO,
        enabled = true,
        shape = MaterialTheme.shapes.medium,
        elevation = Shadows.mainMenuButtonElevation,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = mainMenuModel.colorFamily.containerColor,
            contentColor = mainMenuModel.colorFamily.onContainerColor
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(mainMenuModel.colorFamily.leadingContainerColor)
                    .size(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = mainMenuModel.icon,
                    contentDescription = stringResource(id = mainMenuModel.title)
                )
            }
            Text(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(vertical = Padding.medium),
                text = stringResource(id = mainMenuModel.title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall.copy(color = mainMenuModel.colorFamily.onContainerColor)
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@DefaultPreview
private fun MainMenuButtonPreview() {
    SudokuTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            MainMenuButton(
                mainMenuModel = MainMenuModel.StartGame,
                onClick = {}
            )
        }
    }
}