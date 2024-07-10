import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import moe.tlaster.precompose.PreComposeApp
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

                val navigator = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(navigator)
                }
            }
        }
    }

}



