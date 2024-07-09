package ui

import ResponseData
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.BlindsClosed
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.ClosedCaption
import androidx.compose.material.icons.filled.CurtainsClosed
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LockPerson
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.PersonRemoveAlt1
import androidx.compose.material.icons.filled.PersonalInjury
import androidx.compose.material.icons.filled.Rectangle
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.russhwolf.settings.Settings
import data.TitleTopBarTypes
import data_menu
import getColorsTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import model.ColorIcons
import moe.tlaster.precompose.navigation.Navigator
import vo.ResourceUiState


private val settings: Settings = Settings()


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: ResourceUiState<List<ResponseData>>,
               navigator:Navigator) {

    val colors = getColorsTheme()
    var usuarioState by remember { mutableStateOf("") }

    var sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()


    var keyboardController = LocalSoftwareKeyboardController.current


    var session =  settings.getInt("Session", -1)
    print(session)

    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = "Menú",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            settings.putInt("Session", -1)
                            navigator.navigate("/onboarding")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LockPerson,
                        contentDescription = "Cerrar Sesión"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Cerrar Sesión")
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    ) {

    }


    Column {
        TopAppBar(
            elevation = 1.dp,
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
            actions = {
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    tint = colors.textColor,
                    contentDescription = "More options",
                    modifier = Modifier.padding(end = 16.dp)
                        .size(40.dp)
                        .clickable {
                            scope.launch {
                                sheetState.show()
                            }
                        }
                )
            },
            backgroundColor = colors.backGroundColor

        )

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {

            is ResourceUiState.Success -> {

                val responseData = uiState.data
                print(responseData)

                LazyRow {
                    items(responseData) { responseDataItem ->
                        responseDataItem.data_menu.let { menuList ->
                            menuList.forEachIndexed { index, menu ->     //se puede usar un forEach es sin usar indice
                                MenuItemCard(menu, index, navigator)
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
fun MenuItemCard(menu: data_menu, index: Int, navigator: Navigator) {
    val colorIndex = index % ColorIcons.colors.size


    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(70.dp)  // Ajustar el ancho según sea necesario
            .clickable {
                when (index) {
                    0 -> navigator.navigate("/qrScreen")
                    1 -> navigator.navigate("/marcacionRemota")
                    2 -> navigator.navigate("/verMarcacion")
                }
            }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                .background(ColorIcons.colors[colorIndex])
                .padding(16.dp)
                .size(40.dp),  // Ajustar el tamaño del cuadrado que contiene la imagen
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = menu.imagen,
                contentDescription = menu.textoMenu,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()  // Usar fillMaxSize para ocupar todo el espacio disponible
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
