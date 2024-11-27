package ru.plumsoftware.sudoku.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

//import androidx.compose.ui.text.googlefonts.GoogleFont
//import androidx.compose.ui.text.googlefonts.Font
import ru.plumsoftware.sudoku.R

//internal val provider = GoogleFont.Provider(
//    providerAuthority = "com.google.android.gms.fonts",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)
//
//internal val bodyFontFamily = FontFamily(
//    Font(
//        googleFont = GoogleFont("Balsamiq Sans"),
//        fontProvider = provider,
//    )
//)
//
//internal val displayFontFamily = FontFamily(
//    Font(
//        googleFont = GoogleFont("Balsamiq Sans"),
//        fontProvider = provider,
//    )
//)

internal val baseFont =
    FontFamily(
        Font(R.font.balsamiq_sans_bold, FontWeight.Bold),
        Font(R.font.balsamiq_sans_bold, FontWeight.Black),
        Font(R.font.balsamiq_sans_regular, FontWeight.Normal),
    )

internal val baseline = Typography()

val appTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = baseFont),
    displayMedium = baseline.displayMedium.copy(fontFamily = baseFont),
    displaySmall = baseline.displaySmall.copy(fontFamily = baseFont),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = baseFont),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = baseFont),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = baseFont),
    titleLarge = baseline.titleLarge.copy(fontFamily = baseFont),
    titleMedium = baseline.titleMedium.copy(fontFamily = baseFont),
    titleSmall = baseline.titleSmall.copy(fontFamily = baseFont),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = baseFont),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = baseFont),
    bodySmall = baseline.bodySmall.copy(fontFamily = baseFont),
    labelLarge = baseline.labelLarge.copy(fontFamily = baseFont),
    labelMedium = baseline.labelMedium.copy(fontFamily = baseFont),
    labelSmall = baseline.labelSmall.copy(fontFamily = baseFont),
)