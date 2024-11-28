package ru.plumsoftware.sudoku.ui.dialog.exit

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.plumsoftware.sudoku.R
import ru.plumsoftware.sudoku.ui.model.DefaultPreview
import ru.plumsoftware.sudoku.ui.theme.extensions.Padding
import ru.plumsoftware.sudoku.ui.theme.extensions.Space

@Composable
fun ExitAppDialog(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize()
            .padding(horizontal = Padding.large, vertical = Padding.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.exit_app_dialog),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(height = Space.ExitAppDialog.heading))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                modifier = Modifier.weight(1.0f),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.error
                ),
                onClick = {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            ) {
                Text(
                    text = stringResource(id = R.string.exit),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            VerticalDivider(
                modifier = Modifier.height(height = 20.dp)
            )
            TextButton(
                modifier = Modifier.weight(1.0f),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                onClick = {
                    navHostController.navigateUp()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.cansel),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@DefaultPreview
fun ExitAppDialogPreview() {
    Scaffold {
        ExitAppDialog(rememberNavController())
    }
}