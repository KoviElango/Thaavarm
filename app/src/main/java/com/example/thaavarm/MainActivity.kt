package com.example.thaavarm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thaavarm.ui.theme.ThaavarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThaavarmTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Log.d("MainActivity", "App started. Developer: Koovendhan Elango (AKA) Kovi, Version: 1.0.0, Date: 2024-07-12")
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    var permissionGranted by remember { mutableStateOf(false) }

    RequestCameraPermission { granted ->
        permissionGranted = granted
    }

    if (permissionGranted) {
        MyApp()
    } else {
        Log.d("MainActivity", "Camera permission denied")
        // Optionally, you can display a UI indicating the permission is required
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
