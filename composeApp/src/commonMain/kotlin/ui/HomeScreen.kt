package ui

import ResponseData
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil3.compose.AsyncImage
import data_menu
import getColorsTheme
import model.ColorIcons
import moe.tlaster.precompose.navigation.Navigator
import presentacion.ResourceUiState

@Composable
fun HomeScreen(
    uiState: ResourceUiState<List<ResponseData>>,
    navigator: Navigator
) {
    val colors = getColorsTheme()
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
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
                print(responseData)

                LazyRow {
                    items(responseData) { responseDataItem ->
                        responseDataItem.data_menu.let { menuList ->
                            menuList.forEachIndexed { index, menu ->     //se puede usar un forEach es sin usar indice
                                MenuItemCard(menu, index)
                            }
                        }
                    }
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

@Composable
fun MenuItemCard(menu: data_menu, index: Int) {
    val colorIndex = index % ColorIcons.colors.size

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(60.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(ColorIcons.colors[colorIndex])
                .padding(16.dp)
                .size(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = menu.imagen,
                contentDescription = menu.textoMenu,
                modifier = Modifier
                    .size(20.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = menu.textoMenu,
            fontSize = 6.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            textAlign = TextAlign.Center,
            lineHeight = 10.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 3.dp)
        )
    }

}
