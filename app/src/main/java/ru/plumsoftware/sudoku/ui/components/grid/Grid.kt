package ru.plumsoftware.sudoku.ui.components.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import more.math.matrix.model.Matrix
import ru.plumsoftware.domain.model.SudokuItem
import ru.plumsoftware.sudoku.ui.theme.defaultFullGrid
import ru.plumsoftware.sudoku.ui.theme.defaultUserGrid
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.disabled
import ru.plumsoftware.sudoku.ui.theme.gridColor

@Composable
fun Grid(
    sudokuMatrix: Matrix<SudokuItem>,
    onClick: (Int, Int) -> Unit
) {
    val itemsList: List<SudokuItem> = sudokuMatrix.rows.flatMap { it }
    var selected by rememberSaveable { mutableIntStateOf(-1) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(9),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = Padding.medium)
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background.disabled()),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(itemsList) { index, item ->
            GridCell(
                sudokuItem = item,
                container = if (item.isVisible) defaultFullGrid else if (index == selected) defaultUserGrid else gridColor,
//                text = if (item.isVisible) item.number.toString() else "",
                text = item.number.toString(),
                onClick = {

                    val row = index / 9
                    val col = index % 9

                    onClick(row, col)
                    selected = index
                }
            )
        }
    }
}
