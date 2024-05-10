package com.example.photogalleryapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {

    val homeDestination = Screen.Home.route
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: homeDestination

    val bottomBarScreens = listOf(Screen.ComposeGallery.route, Screen.Home.route, Screen.Profile.route)

    val bottomBar = @Composable {
        val items = listOf(
            BottomNav.System,
            BottomNav.Light,
            BottomNav.Dark,
        )

        val onClick: (String) -> Unit = {
            navController.navigate(it) {
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
            if (currentRoute in bottomBarScreens) { bottomBar() }
        }
//        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
//            startDestination = Screen.ComposeGallery.route,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable(Screen.Settings.route) { SettingsScreen(navController) }
            composable(Screen.ComposeGallery.route) { ComposeGalleryMainComposable() }

            // let's change light/dark mode instead
            composable(BottomNav.Dark.route) { SettingsScreen(navController) }
            composable(BottomNav.Light.route) { ProfileScreen(navController) }
            composable(BottomNav.System.route) { HomeScreen(navController) }
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
fun HomeScreen(navController: androidx.navigation.NavController) {
    val x = object{}::class.java.enclosingMethod?.name
    Log.d("xylo", "HS: class name is $x")
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
fun DefaultPreview() {
    MainScreen()
}


sealed class Screen(val route: String, val name: String) {
    object Home : Screen("home", "Home")
    object Profile : Screen("profile", "Profile")
    object Settings : Screen("settings", "Settings")
    object ComposeGallery : Screen("compose_gallery", "Compose Gallery")
}
