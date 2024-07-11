package com.example.thaavarm

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ResultScreenUI(imageUri: String?, onBackClick: () -> Unit) {
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
        MainButton(
            text = "Back to Camera",
            onClick = onBackClick,
            isCamera = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenUIPreview() {
    ResultScreenUI(
        imageUri = "android.resource://com.example.thaavarm/drawable/ic_launcher_foreground",
        onBackClick = {}
    )
}
