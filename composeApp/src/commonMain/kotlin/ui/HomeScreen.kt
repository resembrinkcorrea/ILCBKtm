package ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getColorsTheme
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun HomeScreen(navigator: Navigator) {
    val colors = getColorsTheme()

    TopAppBar(
        elevation = 0.dp,
        title = {
            Text(
                text = "ILCB",
                fontSize = 25.sp,
                color = colors.textColor
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                imageVector = Icons.Default.Dehaze,
                tint = colors.textColor,
                contentDescription = "Dashboard back"
            )
        },
        backgroundColor = colors.backGroundColor
    )
}