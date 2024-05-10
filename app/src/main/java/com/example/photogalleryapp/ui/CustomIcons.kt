package com.example.photogalleryapp.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedIcon() {
    var animationType by remember { mutableStateOf(0) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (animationType % 3 == 1) 360f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    val alphaValue by animateFloatAsState(
        targetValue = if (animationType % 3 == 2) 0f else 1f,
        animationSpec = tween(durationMillis = 1000)
    )
    val scaleValue by animateFloatAsState(
        targetValue = if (animationType % 3 == 0) 1f else 0.75f,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(modifier = Modifier.clickable { animationType++ }) {
        Image(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Animated Icon",
            modifier = Modifier
                .size(48.dp)
                .graphicsLayer(
                    rotationZ = rotationAngle,
                    alpha = alphaValue,
                    scaleX = scaleValue,
                    scaleY = scaleValue
                )
        )
    }
}

@Preview
@Composable
private fun PreviewAnimatedIcon() {
    AnimatedIcon()
}
