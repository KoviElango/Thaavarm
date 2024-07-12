package com.example.thaavarm

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraScreen(navController: NavController) {
    val outputDirectory = LocalContext.current.filesDir
    val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    val snackbarHostState = remember { SnackbarHostState() }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        content = { innerPadding ->
            CameraView(
                outputDirectory = outputDirectory,
                executor = cameraExecutor,
                onImageCaptured = { uri ->
                    val encodedUri = URLEncoder.encode(uri.toString(), StandardCharsets.UTF_8.toString())
                    Log.d("CameraScreen", "Navigating to URI: $encodedUri")
                    navController.navigate("result/$encodedUri")
                },
                onError = { exc ->
                    errorMessage = exc.message
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    )

    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Dismiss"
            )
            errorMessage = null
        }
    }
}