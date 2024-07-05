package ui

import ResponseData
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getColorsTheme
import moe.tlaster.precompose.navigation.Navigator
import vo.ResourceUiState

@Composable
fun HomeScreen(
    uiState: ResourceUiState<List<ResponseData>>,
               navigator:Navigator) {

    val colors = getColorsTheme()
    var usuarioState by remember { mutableStateOf("") }

    TopAppBar(
        elevation = 0.dp,
        title = {
            Text(
                text = "ILCB",
                fontSize = 25.sp,
                color = colors.textColor
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                imageVector = Icons.Default.Dehaze,
                tint = colors.textColor,
                contentDescription = "Dashboard back"
            )
        },
        backgroundColor = colors.backGroundColor

    )

    when (uiState) {

        is ResourceUiState.Success -> {

            val responseData = uiState.data
            val firstMenu = responseData.firstOrNull()?.data_menu?.getOrNull(0)
            val colaborador = responseData.firstOrNull()?.data_colaborador?.getOrNull(0)
            print(responseData)

            Text(
                        text = "${colaborador?.pers_nombre}  ${colaborador?.perf_nombre}",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(16.dp)
                    )


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