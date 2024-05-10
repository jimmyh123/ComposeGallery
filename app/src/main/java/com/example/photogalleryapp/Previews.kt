package com.example.photogalleryapp

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.photogalleryapp.ui.theme.PhotoGalleryAppTheme

@Composable
fun RenderPreview(content: @Composable () -> Unit) = PhotoGalleryAppTheme(content = content)

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "Light mode group"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "Dark mode group"
)
annotation class LightAndDarkPreviews