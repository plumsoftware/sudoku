package ru.plumsoftware.sudoku.ui.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import more.math.matrix.model.Matrix
import ru.plumsoftware.core.repository.sudoku.SudokuRepositoryImpl
import ru.plumsoftware.domain.model.SudokuItem
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.components.grid.Grid
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.LandScapePreview
import ru.plumsoftware.sudoku.ui.model.PortraitPreview
import ru.plumsoftware.sudoku.ui.screen.game.model.Event
import ru.plumsoftware.sudoku.ui.screen.global.model.GlobalState
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme
import ru.plumsoftware.sudoku.ui.theme.extensions.Blur
import ru.plumsoftware.sudoku.ui.theme.extensions.Space
import ru.plumsoftware.sudoku.ui.theme.extensions.disabled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game(navHostController: NavHostController, globalState: State<GlobalState>) {

    val viewModel = viewModel {
        GameViewModel(
            sudokuRepository = SudokuRepositoryImpl()
        )
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(
            Event.InitSudokuField(
                sudokuDifficulty = globalState.value.sudokuDifficulty,
                sudokuAreaSize = globalState.value.sudokuAreaSize
            )
        )
    }

    val state = viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .blur(Blur.default),
        containerColor = MaterialTheme.colorScheme.onBackground.disabled(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.wrapContentHeight(),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background.disabled()
                ),
                title = {
                    Text(
                        text = "${stringResource(id = R.string.game_in_difficulty)}\n${globalState.value.sudokuDifficulty}",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.navigateUp()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Grid(
                sudokuMatrix = state.value.sudokuMatrix,
                onClick = { sudokuItem ->
                    viewModel.onEvent(
                        Event.ChangeSelectedMatrixItem(
                            item = sudokuItem
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(height = Space.large))
        }
    }
}

private fun findInMatrix(matrix: Matrix<SudokuItem>, target: SudokuItem): Pair<Int, Int> {
    for (rowIndex in matrix.rows.indices) {
        val row = matrix.rows[rowIndex]
        for (colIndex in row.indices) {
            return if (row[colIndex] == target) {
                Pair(rowIndex, colIndex)
            } else {
                Pair(-1, -1)
            }
        }
    }
    return Pair(-1, -1)
}

@Composable
@DefaultPreview
@LandScapePreview
@PortraitPreview
private fun GamePreview() {
    SudokuTheme {
        Game(rememberNavController(), MutableStateFlow(GlobalState()).collectAsState())
    }
}