package Carousel

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import model.ImagesCarousel
import org.jetbrains.compose.resources.painterResource

@Composable
fun Carousel(onFinished: () -> Unit) {
    var currentIndex by remember { mutableStateOf(0) }
    val images = ImagesCarousel.images

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val painter = painterResource(images[currentIndex])
        AnimatedCarouselItem(
            painter = painter,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(12.dp)
                        .background(
                            color = if (index == currentIndex) Color.White else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        // Automatically advance to the next image
        LaunchedEffect(currentIndex) {
            delay(1000) // Adjust delay time as needed
            if (currentIndex < images.size - 1) {
                currentIndex++
            } else {
                onFinished()
            }
        }
    }
}

@Composable
fun AnimatedCarouselItem(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .animateContentSize()
    ) {
        if (visible) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(1000) // Adjust delay time as needed
        visible = false
        delay(100) // Adjust delay time as needed
        visible = true
    }
}