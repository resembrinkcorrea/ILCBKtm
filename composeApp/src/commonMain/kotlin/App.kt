import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Dehaze
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


    PreComposeApp {

        KoinContext {
            val colors = getColorsTheme()

            AppTheme {

                val navigator = rememberNavigator()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(navigator)
                }
            }
        }
    }

}



