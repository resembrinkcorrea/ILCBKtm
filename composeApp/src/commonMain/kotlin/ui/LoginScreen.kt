package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onLoginClicked: (String, String) -> Unit,
    onTextChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
){
    val colors = getColorsTheme()


    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

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
                    usuario = it
                    onTextChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextFieldComponent(
                labelValue = "CONTRASEÑA",
                painterResource = UtilsIcons.PASSWORD.icon,
                onTextSelected = {
                    contrasena = it
                    onPasswordChanged(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            CheckboxComponent(
                value = "Recordarme",
                onTextSelected = onTextChanged,
                onCheckedChange = onCheckedChange
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                value = "Ingresar",
                onButtonClicked = {
                    onLoginClicked.invoke(usuario,contrasena)
                    println(uiState)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(Res.drawable.microsoft), contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}
