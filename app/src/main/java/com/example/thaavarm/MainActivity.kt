package com.example.thaavarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        content = { innerPadding ->
            NavHost(navController = navController, startDestination = "camera", Modifier.padding(innerPadding)) {
                composable("camera") {
                    CameraScreen(navController)
                }
                composable(
                    route = "result/{imageUri}",
                    arguments = listOf(navArgument("imageUri") { type = NavType.StringType })
                ) { backStackEntry ->
                    val imageUri = backStackEntry.arguments?.getString("imageUri")
                    ResultScreen(navController, imageUri)
                }
            }
        }
    )
}
