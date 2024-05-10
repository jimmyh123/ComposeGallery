package com.example.photogalleryapp

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AllAvailableButtons() {
    Column (
        verticalArrangement = Arrangement.spacedBy(Dimensions.tinyMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FancyButton()
        ScalingButton()
        ColorChangeButton()
        PressEffectButton()
        SizeChangeButton()
    }
}

@Preview
@Composable
private fun PreviewAllCustomButtons() {
    AllAvailableButtons()
}


@Composable
fun FancyButton() {
    var clicked by remember { mutableStateOf(false) }

    val scaleValue by animateFloatAsState(
        targetValue = if (clicked) 1f else 0.75f,
        animationSpec = tween(durationMillis = 1000), label = "scale_change_animation"
    )

    Button(
        onClick = { clicked = !clicked },
        modifier = Modifier
            .scale(scaleValue)
    ) {
        Text("Toggle Size")
    }
}

@Composable
fun ScalingButton() {
    val scale = remember { Animatable(1f) }
    var clicked = remember { false }

    Button(
        onClick = {
            clicked = !clicked  // Toggle to trigger LaunchedEffect
        },
        modifier = Modifier.graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        }
    ) {
        Text("Click Me")
    }

    LaunchedEffect(clicked) {
        // This coroutine will automatically restart when `clicked` changes.
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
fun ColorChangeButton() {
    var clicked by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (clicked) Color.Magenta else Color.Gray,
        label = "colour_change_animation"
    )

    Button(
        onClick = { clicked = !clicked },
        modifier = Modifier
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor),
    ) {
        Text("Click Me")
    }
}

@Composable
fun PressEffectButton() {
    var pressed by remember { mutableStateOf(false) }
    val padding by animateDpAsState(
        targetValue = if (pressed) 16.dp else 8.dp,
        label = "press_effect_animation"
    )

    Button(
        onClick = { pressed = !pressed },
        modifier = Modifier.padding(padding)
    ) {
        Text("Press Me")
    }
}

@Composable
fun SizeChangeButton() {
    var clicked by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (clicked) 200.dp else 150.dp,
        label = "size_change_animation"
    )

    Button(
        onClick = { clicked = !clicked },
        modifier = Modifier.size(size)
    ) {
        Text("Toggle Size")
    }
}

@Preview
@Composable
private fun PreviewFancyButton() {
    FancyButton()
}

@Preview
@Composable
private fun PreviewScalingButton() {
    ScalingButton()
}

@Preview
@Composable
private fun PreviewColorChangeButton() {
    ColorChangeButton()
}

@Preview
@Composable
private fun PreviewPressEffectButton() {
    PressEffectButton()
}

@Preview
@Composable
private fun PreviewSizeChangeButton() {
    SizeChangeButton()
}
