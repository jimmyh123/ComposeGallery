package com.example.photogalleryapp

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.photogalleryapp.ui.theme.PhotoGalleryAppTheme

@Composable
fun MainScreen() {

    var theme by remember { mutableStateOf(ThemeType.SYSTEM) }
    PhotoGalleryAppTheme(
        darkTheme = when (theme) {
            ThemeType.LIGHT -> false
            ThemeType.DARK -> true
            ThemeType.SYSTEM -> isSystemInDarkTheme()
        }
    ) {
        val homeDestination = Screen.Home.route
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route ?: homeDestination

        val standardBottomBarScreens =
            listOf(Screen.Home.route, Screen.Profile.route)

        val systemBottomBarScreens = listOf(
            Screen.ComposeGallery.route,
            BottomNav.System.route,
            BottomNav.Light.route,
            BottomNav.Dark.route,
        )

        val systemBottomBar = @Composable {
            val items = listOf(
                BottomNav.System,
                BottomNav.Light,
                BottomNav.Dark,
            )

            val onClick: (BottomNav) -> Unit = {

                navController.navigate(it.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            BottomNavigationBar(items, onClick)
        }

        Scaffold(
            topBar = { TopBar(navController) },
            bottomBar = {
                if (currentRoute in standardBottomBarScreens) {
                    Log.d("xylo", "MainScreen: don't show bottom bar, current route = $currentRoute")
                    // TODO not yet implemented
                } else if (currentRoute in systemBottomBarScreens) {
                    Log.d("xylo", "MainScreen: DO show bottom bar, current route = $currentRoute")
                    systemBottomBar()
                } else {
                    Log.d("xylo", "MainScreen: don't show bottom bar, current route = $currentRoute")
                }
            }
//        bottomBar = { BottomNavigationBar(navController) },
        ) { innerPadding ->
            Log.d("xylo", "MainScreen: calling this block")
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
//            startDestination = Screen.ComposeGallery.route,
                startDestination = Screen.Home.route,
            ) {
//                if (currentRoute in systemBottomBarScreens) {
//                    Log.d("xylo", "MainScreen: Use manual light/dark mode")
//                } else {
//                    Log.d("xylo", "MainScreen: use system")
//                }

                composable(Screen.Home.route) { HomeScreen(navController) }
                composable(Screen.Profile.route) { ProfileScreen(navController) }
                composable(Screen.Settings.route) { SettingsScreen(navController) }
                composable(Screen.ComposeGallery.route) { ComposeGalleryMainComposable() }

                // let's change light/dark mode instead
                composable(BottomNav.Dark.route) {
                    Log.d("xylo", "MainScreen: DARK mode")
                    theme = ThemeType.DARK
                    ComposeGalleryMainComposable()
                }
                composable(BottomNav.Light.route) {
                    Log.d("xylo", "MainScreen: LIGHT mode")
                    theme = ThemeType.LIGHT
                    ComposeGalleryMainComposable()
                }
                composable(BottomNav.System.route) {
                    theme = ThemeType.SYSTEM
                    ComposeGalleryMainComposable()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    showShareButton: Boolean = true,
    showSettingsButton: Boolean = true,
    onShareButtonClicked: (() -> Unit)? = null,
    ) {
    val destination = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute = destination?.route ?: ""

    val showBackArrow = navController.previousBackStackEntry != null

    val backNavArrow = @Composable {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
        }
    }
    val blankIcon = @Composable {}

    val shareButton = @Composable {
        IconButton(onClick = { onShareButtonClicked?.let { it() } }) {
            Icon(Icons.Filled.Share, null)
        }
    }

    val settingsButton = @Composable {
        IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
            Icon(Icons.Filled.Settings, null)
        }
    }

    TopAppBar(
        title = { Text(text = currentRoute) },
        navigationIcon = if (showBackArrow) backNavArrow else blankIcon,
        actions = {
            if (showShareButton) shareButton()
            if (showSettingsButton) settingsButton()
        }
    )
}

//@Composable
//fun BottomBarExample() {
//    var selectedItem by remember { mutableStateOf(0) }
//    val items = listOf(
//        BottomBarItem("Light", Icons.Filled.Home),
//        BottomBarItem("Dark", Icons.Filled.Favorite)
//    )
//
//    BottomNavigation(
//        backgroundColor = MaterialTheme.colors.primary,
//        contentColor = MaterialTheme.colors.onPrimary,
//        elevation = 8.dp
//    ) {
//        items.forEachIndexed { index, item ->
//            BottomNavigationItem(
//                icon = { Icon(item.icon, contentDescription = item.title) },
//                label = { Text(item.title) },
//                selected = selectedItem == index,
//                onClick = { selectedItem = index },
//                alwaysShowLabel = true, // This forces the label to always be shown below the icon
//                selectedContentColor = MaterialTheme.colors.secondary,
//                unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
//            )
//        }
//    }
//}
//
//data class BottomBarItem(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)




@Composable
fun HomeScreen(navController: NavController) {
//    val x = object {}::class.java.enclosingMethod?.name
//    Log.d("xylo", "HS: class name is $x")
    Column {
        Button(onClick = { navController.navigate(Screen.Settings.route) }) {
            Text("Go to Settings Screen")
        }

        Button(onClick = { navController.navigate(Screen.ComposeGallery.route) }) {
            Text("Go to Compose Gallery")
        }
    }
}

@Composable
fun ProfileScreen(navController: androidx.navigation.NavController) {
    Button(onClick = { navController.navigate(Screen.Home.route) }) {
        Text("Go to Home Screen")
    }
}

@Composable
fun SettingsScreen(navController: androidx.navigation.NavController) {
    Button(onClick = { navController.navigate(Screen.Profile.route) }) {
        Text("Go to Profile Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = RenderPreview {
    MainScreen()
}


sealed class Screen(val route: String, val name: String) {
    object Home : Screen("home", "Home")
    object Profile : Screen("profile", "Profile")
    object Settings : Screen("settings", "Settings")
    object ComposeGallery : Screen("compose_gallery", "Compose Gallery")
}

enum class ThemeType { LIGHT, DARK, SYSTEM }
