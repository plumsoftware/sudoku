package ru.plumsoftware.sudoku.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.theme.extensions.disabled

@Composable
fun Life(onClick: (Boolean) -> Unit) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f),
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.game_life),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            Checkbox(
                checked = isSelected,
                onCheckedChange = {
                    isSelected = it
                    onClick(isSelected)
                }
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            softWrap = true,
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.game_life_sub),
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground.disabled())
        )
    }
}