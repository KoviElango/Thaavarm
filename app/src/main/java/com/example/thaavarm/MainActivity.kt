package com.example.thaavarm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thaavarm.ui.theme.ThaavarmTheme

//reach out to https://github.com/KoviElango or kovendhanelango@gmail.com for any questions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThaavarmTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Log.d("MainActivity", "App started. Developer: Koovendhan Elango (AKA) Kovi, Version: 1.0.0, Date: 2024-07-12")
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "camera") {
        composable("camera") { CameraScreen(navController) }
        composable("result/{imageUri}") { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri")
            ResultScreen(navController, imageUri)
        }
    }
}
