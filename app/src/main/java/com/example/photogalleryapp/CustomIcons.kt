package com.example.photogalleryapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.photogalleryapp.Dimensions.tinyMargin
import kotlinx.coroutines.launch

@Composable
fun AllAvailableIcons() {
    Column (
        verticalArrangement = Arrangement.spacedBy(tinyMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedClickableHeartIcon()
        ShareIcon()
    }
}

@Preview
@Composable
private fun PreviewAllIcons() = RenderPreview {
    AllAvailableIcons()
}

@Composable
fun AnimatedClickableHeartIcon(
    clickedColour: Color = Color.Red,
    unClickedColour: Color = Color.Gray
) {

    var clicked by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (clicked) clickedColour else unClickedColour,
        label = "colour_change_animation"
    )
    val scale = remember { Animatable(1f) }

    val icon = Icons.Filled.Favorite
    Icon(
        icon,
        contentDescription = icon.name,
        tint = backgroundColor,
        modifier = Modifier
            .clickable { clicked = !clicked }
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            }
    )

    LaunchedEffect(clicked) {
        launch {
            scale.animateTo(
                targetValue = 1.2f,
                animationSpec = tween(durationMillis = 300)
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }
}

@Composable
fun ShareIcon(
    colour: Color = Color.Green
) {
    val icon = Icons.Filled.Share
    Icon(
        icon,
        contentDescription = icon.name,
        tint = colour,
        modifier = Modifier
            .clickable {  }
    )
}


@Preview
@Composable
private fun PreviewAnimatedClickableHeartIcon() {
    AnimatedClickableHeartIcon()
}

@Preview
@Composable
private fun PreviewShareIcon() {
    ShareIcon()
}