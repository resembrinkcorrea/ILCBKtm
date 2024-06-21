import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp),
        )
    ) {

        content()

    }
}

@Composable
fun getColorsTheme() : DarkModeColors {
    val isDarkMode = false

    val Purple = Color(0xFF6A66FF)
    val ColorExpenseItem = if (isDarkMode) Color(0xFF090808) else Color(0xFFF1F1F1)
    val BackGroundColor = if (isDarkMode) Color(0xFF1E1C1C) else Color.White
    val TextColor =  if (isDarkMode) Color.White else Color.Black
    val AddIconColor =  if (isDarkMode) Purple else Color.Black
    val ColorArrowRound =  if (isDarkMode) Purple else Color.Gray.copy(alpha = .2F)

    return DarkModeColors(
        purple = Purple,
        colorExpenseItem = ColorExpenseItem,
        backGroundColor = BackGroundColor,
        textColor = TextColor,
        addIconColor = AddIconColor,
        colorArrowRound = ColorArrowRound
    )


}

data class DarkModeColors(
    val purple: Color,
    val colorExpenseItem: Color,
    val backGroundColor: Color,
    val textColor: Color,
    val addIconColor: Color,
    val colorArrowRound: Color
)