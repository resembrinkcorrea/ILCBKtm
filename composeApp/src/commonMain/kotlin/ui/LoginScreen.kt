package ui

import RepoImpl
import ResponseData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russhwolf.settings.Settings
import components.ButtonComponent
import components.CheckboxComponent
import components.MyTextFieldComponent
import components.PasswordTextFieldComponent
import getColorsTheme
import ilcbktm.composeapp.generated.resources.Res
import ilcbktm.composeapp.generated.resources.logoilcb
import ilcbktm.composeapp.generated.resources.microsoft
import model.UtilsIcons
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.painterResource
import vo.ResourceUiState


private val settings:Settings = Settings()
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    uiState: ResourceUiState<List<ResponseData>>,
    onLoginClicked: (String, String) -> Unit,
    navigator:Navigator
){
    val colors = getColorsTheme()

    var usuarioState by remember { mutableStateOf("") }
    var contrasenaState by remember { mutableStateOf("") }
    var rememberMeState by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(Res.drawable.logoilcb), contentDescription = "",
                modifier = Modifier.size(270.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            MyTextFieldComponent(
                labelValue = "Correo Institucional",
                painterResource = UtilsIcons.MESSAGE.icon,
                onTextChanged = {
                    usuarioState = it
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextFieldComponent(
                labelValue = "CONTRASEÑA",
                painterResource = UtilsIcons.PASSWORD.icon,
                onTextSelected = {
                    contrasenaState = it
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            CheckboxComponent(
                value = "Recordarme",
                onTextSelected = {

                },
                onCheckedChange = {isChecked ->
                    rememberMeState = isChecked
                    settings.putInt("Session", if(isChecked) 1 else -1)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(
                value = "Ingresar",
                onButtonClicked = {
                    onLoginClicked.invoke(usuarioState,contrasenaState)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.microsoft), contentDescription = "",
                modifier = Modifier.size(150.dp)
            )

            when (uiState) {

                is ResourceUiState.Success -> {

                    val responseData = uiState.data
                    val firstMenu = responseData.firstOrNull()?.data_menu?.getOrNull(0)
                    val colaborador = responseData.firstOrNull()?.data_colaborador?.getOrNull(0)

                   // println(colaborador?.empl_url_foto)
                    print(responseData)

//                    Text(
//                        text = "${colaborador?.pers_nombre}  ${colaborador?.perf_nombre}",
//                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
//                        modifier = Modifier.padding(16.dp)
//                    )
                    navigator.navigate("/homeScreen")

                }
                is ResourceUiState.Error -> {
                    val errorMessage = uiState.message
                    Text(
                        text = "Error: $errorMessage",
                        style = MaterialTheme.typography.body1,
                        color = colors.textColor
                    )
                }
                else -> {
                    // Puedes manejar otros estados como Loading aquí si es necesario
                }
            }

        }
    }
}
