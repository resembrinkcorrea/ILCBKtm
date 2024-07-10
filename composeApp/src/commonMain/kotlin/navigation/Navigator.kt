package navigation

import RepoImpl
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import getColorsTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import org.koin.core.parameter.parametersOf
import presentacion.QrViewModel
import presentacion.UserViewModel
import presentacion.UsuarioCorreoViewModel
import ui.HomeScreen
import ui.LoginScreen
import ui.OnBoardingScreen
import ui.QrScreen

@Composable
fun Navigation(navController: NavController) {

    val colors = getColorsTheme()

    val httpClient = remember {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    val viewModel: UserViewModel = viewModel(modelClass = UserViewModel::class) {
        UserViewModel(RepoImpl(httpClient))
    }

    val viewModelCorreo: UsuarioCorreoViewModel = viewModel(modelClass = UsuarioCorreoViewModel::class) {
        UsuarioCorreoViewModel(RepoImpl(httpClient))
    }

    val viewModelQr = koinViewModel(QrViewModel::class) {
        parametersOf()
    }

    var usuario = "resembrink.correa@cordonbleu.edu.pe"
    var uneg by remember { mutableStateOf(2) }
    var tipoConexion by remember { mutableStateOf("app movil colaborador") }
    var ipConexion by remember { mutableStateOf("200.123.1.66") }
    var idPersDet = 92578

    NavHost(
        modifier = Modifier.background(colors.backGroundColor),
        navController = navController as NavHostController,
        startDestination = "/onboarding"
    ) {
        composable(route = "/onboarding") {
            OnBoardingScreen(navController)
        }
        composable(route = "/login") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = uiState,
                onLoginClicked = { usr, pwd ->
                    viewModel.setUsuarioRequest(usr, pwd, uneg, tipoConexion, ipConexion)
                },
                navController
            )
        }

        composable(route = "/homeScreen") {
            val uiState by viewModelCorreo.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModelCorreo.setUsuarioRequest(usuario, uneg, tipoConexion, ipConexion)
            }
            HomeScreen(
                uiState = uiState,
               navController
            )
        }

        composable(route = "/qrScreen") {
            val uiState by viewModelQr.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModelQr.setQRequest(idPersDet)
            }
            QrScreen(
                uiState = uiState,
                 navController
            )
        }

        // Agrega otras escenas según sea necesario
    }
}
