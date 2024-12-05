package ru.plumsoftware.sudoku.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.core.repository.settings.SettingsRepositoryImpl
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.model.LandScapePreview
import ru.plumsoftware.sudoku.ui.model.PortraitPreview
import ru.plumsoftware.sudoku.ui.screen.settings.model.Event
import ru.plumsoftware.sudoku.ui.theme.SudokuTheme
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navHostController: NavHostController) {

    val context = LocalContext.current
    val viewModel = viewModel {
        SettingsViewModel(
            settingsRepository = SettingsRepositoryImpl(context = context)
        )
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ru.plumsoftware.sudoku.ui.screen.settings.model.Effect.Back -> {
                    navHostController.navigateUp()
                }
            }
        }
    }

    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
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
                        text = stringResource(id = R.string.settings),
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
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = Padding.large)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = Space.extraLarge,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(height = Space.large))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.weight(1.0f),
                    text = stringResource(id = R.string.theme),
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = state.value.isDark,
                    onCheckedChange = { value ->
                        viewModel.onEvent(Event.ChangeTheme(value = value))
                    }
                )
            }
        }
    }
}

@Composable
@DefaultPreview
@LandScapePreview
@PortraitPreview
private fun SettingsPreview(){
    SudokuTheme {
        Settings(navHostController = rememberNavController())
    }
}