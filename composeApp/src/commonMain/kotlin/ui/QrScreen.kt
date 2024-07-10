package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import getColorsTheme
import io.ktor.util.encodeBase64
import model.ResponseQr
import vo.ResourceUiState


@Composable
fun QrScreen(uiState: ResourceUiState<List<ResponseQr>>,
             navigator:NavController
) {
    val colors = getColorsTheme()


    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        TopAppBar(
            elevation = 1.dp,
            title = {
                Text(
                    text = "Mi QR",
                    fontSize = 25.sp,
                    color = colors.textColor
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigator.navigate("/homeScreen") // Navega de regreso a HomeScreen al hacer clic
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(48.dp),
                        imageVector = Icons.Default.ChevronLeft,
                        tint = colors.textColor,
                        contentDescription = "Dashboard back"
                    )
                }
            },
            backgroundColor = colors.backGroundColor


        )

        when (uiState) {

            is ResourceUiState.Success -> {

                val responseData = uiState.data
                val dataQr = responseData.firstOrNull()?.data_qr?.getOrNull(0)

                val qrBase64 = dataQr?.codigo_qr?.encodeBase64()

                if (!qrBase64.isNullOrEmpty()) {
                    val qrCodeImage = qrgenerator.generateCode(qrBase64)
                    qrCodeImage?.let { imageBitmap ->
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .size(300.dp)  // Tamaño deseado de la imagen
                                .padding(vertical = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                } else {
                    Text(
                        text = "QR Code not available",
                        style = MaterialTheme.typography.body1,
                        color = colors.textColor
                    )
                }
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
