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
import getColorsTheme
import io.ktor.client.HttpClient
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle

import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel
import presentacion.UserViewModel
import ui.LoginScreen

import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.koin.koinViewModel
import org.koin.core.parameter.parametersOf
import presentacion.QrViewModel
import presentacion.UsuarioCorreoViewModel
import ui.HomeScreen
import ui.OnBoardingScreen
import ui.QrScreen

@Composable
fun Navigation(navigator: Navigator) {
    val colors = getColorsTheme()
    val httpClient = remember { HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }}
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

//    BackHandler(onBack = {
//        navigator.goBack()
//    })


    NavHost(
        modifier = Modifier.background(colors.backGroundColor),
        navigator = navigator,
        initialRoute = "/onboarding"
    ) {
        scene(route = "/onboarding") {
            OnBoardingScreen(navigator = navigator)
        }
        scene(route = "/login") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = uiState,
                onLoginClicked = { usr, pwd ->
                    viewModel.setUsuarioRequest(usr, pwd, uneg, tipoConexion, ipConexion)
                },
                navigator = navigator
            )
        }

        scene(route = "/homeScreen") {
            val uiState by viewModelCorreo.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModelCorreo.setUsuarioRequest(usuario, uneg, tipoConexion, ipConexion)
            }
            HomeScreen(
                uiState = uiState,
                navigator = navigator
            )
        }

        scene(route = "/qrScreen") {
            val uiState by viewModelQr.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModelQr.setQRequest(idPersDet)
            }
            QrScreen(
                uiState = uiState,
                navigator = navigator
            )
        }

        // Agrega otras escenas según sea necesario
    }
}

