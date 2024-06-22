import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import themeapp.getColorsTheme

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    PreComposeApp {
        KoinContext {
            val colors = getColorsTheme()

            AppTheme {
                val navigator = rememberNavigator()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            elevation = 0.dp,
                            title = {
                                Text(
                                    text = "Inicio",
                                    fontSize = 25.sp,
                                    color = colors.textColor
                                )
                            },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Default.Apps,
                                    tint = colors.textColor,
                                    contentDescription = "Dashboard back"
                                )
                            },
                            backgroundColor = colors.backGroundColor
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}
