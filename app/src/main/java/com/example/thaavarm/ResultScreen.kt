package com.example.thaavarm

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun ResultScreen(navController: NavController?, imageUri: String?) {
    val decodedUri = imageUri?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
    ResultScreenUI(
        imageUri = decodedUri,
        onBackClick = { navController?.navigate("camera") }
    )
}
