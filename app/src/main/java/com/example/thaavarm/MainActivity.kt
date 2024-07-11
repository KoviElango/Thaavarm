package com.example.thaavarm

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thaavarm.ui.theme.ThaavarmTheme

class MainActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThaavarmTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && permissions.isNotEmpty() && grantResults.isNotEmpty()) {
            val granted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    var hasCameraPermission by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        hasCameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasCameraPermission) {
            ActivityCompat.requestPermissions(
                context as MainActivity,
                arrayOf(Manifest.permission.CAMERA),
                0
            )
        }
    }

    if (hasCameraPermission) {
        NavHost(navController = navController, startDestination = "camera") {
            composable("camera") { CameraScreen(navController) }
            composable("result/{uri}") { backStackEntry ->
                val uri = backStackEntry.arguments?.getString("uri")
                ResultScreen(navController, uri)
            }
        }
    } else {
        RequestCameraPermission { granted ->
            hasCameraPermission = granted
        }
    }
}
