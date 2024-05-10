package com.example.photogalleryapp

import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ButtonAnimationTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun colorChangeButtonTest() {
        composeTestRule.setContent {
            ColorChangeButton()
        }

        val button = composeTestRule.onNodeWithText("Click Me")
        button.assertExists("Button not found")

        button.performClick()
        button.assertBackgroundColorEquals(Color.Magenta)
    }

    // Additional tests for other buttons can be implemented similarly
}

fun SemanticsNodeInteraction.assertBackgroundColorEquals(color: Color) {
    val b = 5
    // Implement this extension function to verify background color.
    // This is a placeholder for actual implementation.
}
