package com.example.photogalleryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.photogalleryapp.Dimensions.largeMargin
import com.example.photogalleryapp.Dimensions.tinyMargin
import com.example.photogalleryapp.ui.theme.PhotoGalleryAppTheme

@Composable
fun ComposeGalleryMainComposable() {
    PhotoGalleryAppTheme {
        val selectedTabIndex = remember { mutableIntStateOf(0) }
        val tabs = remember {
            mutableStateListOf(
                TabData("Buttons"),
                TabData("Icons"),
                TabData("Animations")
            )
        }


        Column {
            ScrollableTabRow(
                selectedTabIndex.intValue,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.intValue]),
//                        height = IndicatorHeight + 1.dp,
//                        color = Theme.colours().material.primary,
                    )
                },
//                backgroundColor = Theme.colours().itemBackground,
//                contentColor = Theme.colours().buttonTextColor,
            ) {
                tabs.forEachIndexed { tabIndex, tab ->
                    Tab(
                        selected = selectedTabIndex.intValue == tabIndex,
//                        unselectedContentColor = Theme.colours().textLight,
                        onClick = { selectedTabIndex.intValue = tabIndex },
                        text = {
                            Text(
                                text = tab.name,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }

            // Display the tabs
            when (selectedTabIndex.intValue) {
                0 -> ButtonsTab()
                1 -> IconsTab()
                2 -> AnimationsTab()
            }
        }
    }
}

@Composable
fun ButtonsTab() {
    GalleryScreenComposable {
        Column(
            modifier = Modifier.padding(horizontal = largeMargin),
            verticalArrangement = Arrangement.spacedBy(tinyMargin),
        ) {
            SectionTitle("Animated buttons", "All the animated buttons available for use in the app")
            FancyButton()
            ScalingButton()
            ColorChangeButton()
            PressEffectButton()
            SizeChangeButton()
        }
    }
}

@Composable
fun IconsTab() {
    GalleryScreenComposable {
        Column(
            modifier = Modifier.padding(horizontal = largeMargin),
            verticalArrangement = Arrangement.spacedBy(tinyMargin),
        ) {
            SectionTitle("Icons", "All the Icons available for use in the app")
            Spacer(Modifier.height(tinyMargin))
            AllAvailableIcons()
        }
    }
}

@Composable
fun AnimationsTab() {

}

@Composable
fun SectionTitle(
    title: String,
    description: String? = null,
) {
    Spacer(Modifier.height(largeMargin))
    Text(title, style = MaterialTheme.typography.labelLarge)

    description?.let {
        Spacer(Modifier.height(tinyMargin))
        Text(it, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
internal fun GalleryScreenComposable(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .verticalScroll(rememberScrollState())
    ) {
        content()
        Spacer(Modifier.height(largeMargin))
    }
}

internal data class TabData(
    val name: String
)