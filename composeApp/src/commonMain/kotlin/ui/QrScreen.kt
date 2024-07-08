package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getColorsTheme
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun QrScreen(
    navigator: Navigator
) {
    val colors = getColorsTheme()

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        TopAppBar(
            elevation = 1.dp,
            title = {
                Text(
                    text = "Mi QR",
                    fontSize = 25.sp,
                    color = colors.textColor
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigator.navigate("/homeScreen") // Navega de regreso a HomeScreen al hacer clic
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(48.dp),
                        imageVector = Icons.Default.ChevronLeft,
                        tint = colors.textColor,
                        contentDescription = "Dashboard back"
                    )
                }
            },
            backgroundColor = colors.backGroundColor
        )
    }
}
