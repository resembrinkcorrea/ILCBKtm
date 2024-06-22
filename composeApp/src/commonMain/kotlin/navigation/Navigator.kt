package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import presentacion.UserViewModel
import ui.LoginScreen


@Composable
fun Navigation(navigator: Navigator) {

    val colors = getColorsTheme()
    val viewModel: UserViewModel = koinViewModel(UserViewModel::class)

    var usuario by remember { mutableStateOf("60768267@cordonbleu.edu.pe") }
    var contrasena by remember { mutableStateOf("O+2b2n5X") }
    var uneg by remember { mutableStateOf(2) }
    var tipoConexion by remember { mutableStateOf("app android estudiante") }
    var ipConexion by remember { mutableStateOf("200.123.1.66") }
    var sistema by remember { mutableStateOf("ANDROID") }

    NavHost(
        modifier = Modifier.background(colors.backGroundColor),
        navigator = navigator,
        initialRoute = "/login"
    ) {
        scene(route = "/login") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = uiState,
                onLoginClicked = {
                    viewModel.setUsuarioRequest(usuario, contrasena, uneg, tipoConexion, ipConexion, sistema)
                },
                onTextChanged = { newText -> usuario = newText },
                onPasswordChanged = { newPassword -> contrasena = newPassword },
                onCheckedChange = { isChecked -> /* Manejar el cambio de estado del checkbox */ }
            )
        }
    }
}