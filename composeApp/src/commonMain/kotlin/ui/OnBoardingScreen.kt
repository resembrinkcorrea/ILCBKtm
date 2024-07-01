package ui

import Carousel.Carousel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import moe.tlaster.precompose.navigation.Navigator


@Composable
fun OnBoardingScreen(navigator: Navigator) {

    var carouselFinished by remember { mutableStateOf(false) }

    if (carouselFinished) {
        navigator.navigate("/login")
    } else {
        Carousel { carouselFinished = true }
    }
}


