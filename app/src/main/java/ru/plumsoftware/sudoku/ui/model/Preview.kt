package ru.plumsoftware.sudoku.ui.model

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_NIGHT_YES,
)
annotation class DefaultPreview

@Preview(
    device = "spec:parent=pixel_7"
)
annotation class PortraitPreview

@Preview(
    device = "spec:parent=pixel_7,orientation=landscape"
)
annotation class LandScapePreview

