package ui

import Carousel.Carousel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.russhwolf.settings.Settings

private val settings: Settings = Settings()
@Composable
fun OnBoardingScreen(navigator: NavController) {

    var carouselFinished by remember { mutableStateOf(false) }

   val session = settings.getInt("Session", -1)

    if(session == -1){
        if (carouselFinished) {
            navigator.navigate("/login")
        } else {
            Carousel { carouselFinished = true }
        }
    }else {
        navigator.navigate("/homeScreen")
    }
}


