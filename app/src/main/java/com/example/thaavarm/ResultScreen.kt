package com.example.thaavarm

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

@Composable
fun ResultScreen(navController: NavController?, imageUri: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        if (imageUri != null) {
            Image(
                painter = rememberImagePainter(Uri.parse(imageUri)),
                contentDescription = "Captured Image",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Plant Name: Dummy Plant Name")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController?.navigate("camera") }) {
            Text("Back to Camera")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val mockNavController = rememberNavController()
    ResultScreen(navController = mockNavController, imageUri = "android.resource://com.example.thaavarm/drawable/ic_launcher_foreground")
}
