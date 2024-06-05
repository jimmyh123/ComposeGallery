package com.example.photogalleryapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(
//    navController: NavController,
    items: List<BottomNav>,
    onClick: (BottomNav) -> Unit,
) {

    val selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNav.System,
        BottomNav.Light,
        BottomNav.Dark,
    )

//    val onClick: (String) -> Unit = {
////        selectedItem = index
//        navController.navigate(it) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = { onClick(item) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() = RenderPreview {

    val navController = rememberNavController()

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

sealed class BottomNav(val name: String, val route: String, val icon: ImageVector) {
    object System: BottomNav("System", "system", Icons.Filled.AccountBox)
    object Light: BottomNav("Light", "light", Icons.Filled.AccountBox)
    object Dark: BottomNav("Dark", "dark", Icons.Filled.DateRange)
}