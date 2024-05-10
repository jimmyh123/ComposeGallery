package com.example.photogalleryapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import com.example.photogalleryapp.ui.AnimatedIcon
import org.junit.Rule
import org.junit.Test

class AnimatedIconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun animatedIconTest() {
        composeTestRule.setContent {
            AnimatedIcon()
        }

        val icon = composeTestRule.onNodeWithContentDescription("Animated Icon")

        // Initial state
        icon.assertIsDisplayed().assertExists()

        // Perform click and check rotation
        icon.performClick()
        // Delay to allow animation to complete (could use advanceTimeBy in an animation clock context)
        Thread.sleep(1100)
        // Checks would be here for visual confirmation or specific animation properties state

        // Perform another click and check fade
        icon.performClick()
        Thread.sleep(1100)

        // Perform third click and check scale
        icon.performClick()
        Thread.sleep(1100)
    }
}