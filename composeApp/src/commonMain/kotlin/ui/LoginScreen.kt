package ui

import androidx.compose.runtime.Composable
import model.ResponseUser

@Composable
fun LoginScreen(uiState : ResponseUser){

//Surface(
//modifier = Modifier.fillMaxSize(),
//color = MaterialTheme.colors.background
//) {
//    Column(
//        modifier = Modifier.fillMaxWidth()
//            .padding(30.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painterResource(Res.drawable.logoilcb), "",
//            modifier = Modifier.size(270.dp)
//        )
//
//        Spacer(modifier = Modifier.height(40.dp))
//
//        MyTextFieldComponent(
//            labelValue = "Correo Institucional",
//            painterResource = UtilsIcons.MESSAGE.icon,
//            onTextChanged = {
//                // Maneja el cambio de texto aquí
//            },
//            errorStatus = true // Ajusta esto según sea necesario
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        PasswordTextFieldComponent(
//            labelValue = "CONTRASEÑA",
//            painterResource = UtilsIcons.PASSWORD.icon,
//            onTextSelected =  {
//
//            }
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//
//        CheckboxComponent(value = "Recordarme",
//            onTextSelected = {
//
//            },
//            onCheckedChange = {
//
//            }
//
//        )
//
//        Spacer(modifier = Modifier.height(30.dp))
//
//        ButtonComponent(
//            value = "Ingresar",
//            onButtonClicked = {
//
//            }
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Image(
//            painterResource(Res.drawable.microsoft), "",
//            modifier = Modifier.size(200.dp)
//        )
//
//
//
//
//    }
}