package ercanduman.recipeapplication.common.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Suppress("LongParameterList")
@Composable
fun RecipeAppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppColorDarkGrey,
    maxLines: Int = Int.MAX_VALUE,
    fontSize: TextUnit = AppTextSize16,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        fontWeight = fontWeight,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = Typography.bodyMedium.fontFamily
    )
}
