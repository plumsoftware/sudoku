package ru.plumsoftware.sudoku.ui.screen.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import ru.plumsoftware.core.repository.sudoku.SudokuRepositoryImpl
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.components.grid.Grid
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.LandScapePreview
import ru.plumsoftware.sudoku.ui.model.PortraitPreview
import ru.plumsoftware.sudoku.ui.screen.game.model.Event
import ru.plumsoftware.sudoku.ui.screen.global.model.GlobalState
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme
import ru.plumsoftware.sudoku.ui.theme.defaultUserGrid
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
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
        viewModel.onEvent(Event.StartTime)
    }

    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(
                    space = Space.medium,
                    alignment = Alignment.Top
                ),
                horizontalAlignment = Alignment.Start
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    title = {
                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            textAlign = TextAlign.Start,
                            text = "${globalState.value.sudokuDifficulty}",
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
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = Padding.large, start = Padding.large, end = Padding.large)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = Space.small,
                        alignment = Alignment.Start
                    )
                ) {
                    val time = SimpleDateFormat(
                        "KK:mm:ss",
                        Locale.getDefault()
                    ).format(Date(state.value.time))

                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.time),
                        contentDescription = time,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(
                space = Space.extraLarge,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Grid(
                sudokuMatrix = state.value.sudokuMatrix,
                selectedGridCell = state.value.selectedGrid,
                onClick = { row, col, gridCell ->
                    viewModel.onEvent(
                        Event.ChangeSelectedMatrixItem(
                            row = row,
                            column = col,
                            gridCell = gridCell
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(height = Space.large))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 5,
                verticalArrangement = Arrangement.spacedBy(
                    space = Space.medium,
                    alignment = Alignment.CenterVertically
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    space = Space.medium,
                    alignment = Alignment.CenterHorizontally
                )
            ) {

                for (i in 1..10) {
                    if (i <= 9)
                        Button(
                            modifier = Modifier.size(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (i == state.value.selectedNumber) defaultUserGrid else MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            shape = MaterialTheme.shapes.small,
                            contentPadding = PaddingValues(all = Padding.small),
                            onClick = {
                                viewModel.onEvent(Event.ChangeSelectedNumber(number = i))
                            }
                        ) {
                            Text(
                                text = i.toString(),
                                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                            )
                        }
                    else
                        Button(
                            modifier = Modifier.size(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (i == state.value.selectedNumber) defaultUserGrid else MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground
                            ),
                            shape = MaterialTheme.shapes.small,
                            contentPadding = PaddingValues(all = Padding.small),
                            onClick = {
                                viewModel.onEvent(Event.ChangeSelectedNumber(number = i))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Refresh,
                                contentDescription = null,
                                modifier = Modifier.rotate(90.0f)
                            )
                        }
                }
            }
        }
    }
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