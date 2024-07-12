package com.example.thaavarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thaavarm.ui.theme.ThaavarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThaavarmTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "camera") {
                    composable("camera") { CameraScreen(navController) }
                    composable("result/{imageUri}") { backStackEntry ->
                        ResultScreen(
                            navController = navController,
                            imageUri = backStackEntry.arguments?.getString("imageUri")
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThaavarmTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "camera") {
            composable("camera") { CameraScreen(navController) }
            composable("result/{imageUri}") { backStackEntry ->
                ResultScreen(
                    navController = navController,
                    imageUri = backStackEntry.arguments?.getString("imageUri")
                )
            }
        }
    }
}
