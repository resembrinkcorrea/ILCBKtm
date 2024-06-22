package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ButtonComponent
import components.CheckboxComponent
import components.MyTextFieldComponent
import components.PasswordTextFieldComponent
import getColorsTheme
import ilcbktm.composeapp.generated.resources.Res
import ilcbktm.composeapp.generated.resources.logoilcb
import ilcbktm.composeapp.generated.resources.microsoft
import model.ResponseUser
import model.UtilsIcons
import org.jetbrains.compose.resources.painterResource
import presentacion.ResourceUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    uiState: ResourceUiState<List<ResponseUser>>,
    onLoginClicked: () -> Unit,
    onTextChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
){

    val colors = getColorsTheme()

    when (uiState) {
        is ResourceUiState.Loading -> {
            println("Cargando")
        }
        is ResourceUiState.Success -> {
            val userList = uiState.data
            // Aquí puedes imprimir en el logcat la respuesta del servidor
            println("Respuesta del servidor: $userList")
        }
        is ResourceUiState.Error -> {
            println("Error: ${uiState.message}")
        }
    }

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
                    // Maneja el cambio de texto aquí
                },
                errorStatus = true // Ajusta esto según sea necesario
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextFieldComponent(
                labelValue = "CONTRASEÑA",
                painterResource = UtilsIcons.PASSWORD.icon,
                onTextSelected = {
                    // Maneja la selección de texto aquí
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            CheckboxComponent(
                value = "Recordarme",
                onTextSelected = {
                    // Maneja la selección de texto aquí
                },
                onCheckedChange = {
                    // Maneja el cambio de estado aquí
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                value = "Ingresar",
                onButtonClicked = onLoginClicked
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.microsoft), contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}