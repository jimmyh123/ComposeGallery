package com.example.photogalleryapp
//
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.photogalleryapp.ui.theme.PhotoGalleryAppTheme
//
//
//@Composable
//fun MainApp() {
//    val navController = rememberNavController()
//    var theme by remember { mutableStateOf(ThemeType.SYSTEM) }
//
//    PhotoGalleryAppTheme(
//        darkTheme = when (theme) {
//            ThemeType.LIGHT -> false
//            ThemeType.DARK -> true
//            ThemeType.SYSTEM -> isSystemInDarkTheme()
//        }
//    ) {
//        NavHost(navController = navController, startDestination = "screenOne") {
//            composable("screenOne") { ScreenOne(navController) }
//            composable("screenTwo") { ScreenTwo { theme = it } }
//        }
//    }
//}
//
//
//@Composable
//fun ScreenOne(navController: NavController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Screen One") })
//        }
//    ) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Button(onClick = { navController.navigate("screenTwo") }) {
//                Text("Go to Screen Two")
//            }
//        }
//    }
//}
//
//enum class ThemeType {
//    LIGHT, DARK, SYSTEM
//}
//
//
//@Composable
//fun ScreenTwo(onThemeChange: (ThemeType) -> Unit) {
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Screen Two") })
//        },
//        bottomBar = {
//            BottomNavigation(elevation = 5.dp) {
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Default.BrightnessLow, contentDescription = "Light") },
//                    label = { Text("Light") },
//                    selected = false,
//                    onClick = { onThemeChange(ThemeType.LIGHT) }
//                )
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Default.Brightness2, contentDescription = "Dark") },
//                    label = { Text("Dark") },
//                    selected = false,
//                    onClick = { onThemeChange(ThemeType.DARK) }
//                )
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Default.Settings, contentDescription = "System") },
//                    label = { Text("System") },
//                    selected = false,
//                    onClick = { onThemeChange(ThemeType.SYSTEM) }
//                )
//            }
//        }
//    ) {
//        // Content of ScreenTwo
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MyPreview() {
//    MainApp()
//}