package com.example.thaavarm

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thaavarm.ui.theme.ThaavarmTheme

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("MainActivity", "Camera permission granted")
            } else {
                Log.d("MainActivity", "Camera permission denied")
            }
        }

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

        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("MainActivity", "Camera permission already granted")
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // You can show your own UI to explain why you need the permission before requesting it
                Log.d("MainActivity", "Showing permission rationale")
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                // Directly request for required permissions, without explanation
                Log.d("MainActivity", "Requesting camera permission")
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
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


//reach out to https://github.com/KoviElango or kovendhanelango@gmail.com for any questions