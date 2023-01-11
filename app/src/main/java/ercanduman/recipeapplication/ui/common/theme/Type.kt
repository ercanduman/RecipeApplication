package ercanduman.recipeapplication.ui.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ercanduman.recipeapplication.R

// Set of Material typography styles to start with
val fonts: FontFamily = FontFamily(
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_regular, weight = FontWeight.Normal)
)

val Typography: Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize16,
        lineHeight = AppTextSize24,
        letterSpacing = AppLetterSpaceHalf
    ),

    titleLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = AppTextSize24,
        lineHeight = AppTextSize28,
        letterSpacing = AppLetterSpaceNone
    )
)
