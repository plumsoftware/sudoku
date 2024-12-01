package ru.plumsoftware.sudoku.ui.components.grid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.plumsoftware.domain.model.SudokuItem
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding

@Composable
fun GridCell(sudokuItem: SudokuItem, text: String, container: Color, onClick: (SudokuItem) -> Unit, ) {
    val colors = ButtonDefaults.buttonColors(
        containerColor = container,
        contentColor = Color.Black,
        disabledContainerColor = container,
        disabledContentColor = Color.Black
    )

    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(all = Padding.extraExtraSmall)
    ) {
        Button(
            modifier = Modifier.align(Alignment.Center).fillMaxSize(),
            shape = MaterialTheme.shapes.small,
            colors = colors,
            enabled = sudokuItem.isUserNumber,
            contentPadding = PaddingValues(all = Padding.small),
            onClick = {
                onClick(sudokuItem)
            }
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = if (sudokuItem.isVisible) FontWeight.Normal else FontWeight.Black
                )
            )
        }
    }
}