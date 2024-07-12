package com.example.thaavarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import android.util.Log

@Composable
fun ResultScreenUI(imageUri: String?, plantName: String, plantFamily: String, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageUri != null) {
                Log.d("ResultScreenUI", "Image URI: $imageUri")
                Image(
                    painter = rememberImagePainter(
                        data = imageUri,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Captured Image",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(Color.Black),
                    contentScale = ContentScale.Crop
                )
            } else {
                Log.d("ResultScreenUI", "Image URI is null")
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = "Plant: $plantName")
            Text(text = "Family: $plantFamily")
        }

        MainButton(
            onClick = onBackClick,
            isCamera = false,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenUIPreview() {
    ResultScreenUI(
        imageUri = "https://via.placeholder.com/300",
        plantName = "Dummy Plant Name",
        plantFamily = "Dummy Family Name",
        onBackClick = {}
    )
}
