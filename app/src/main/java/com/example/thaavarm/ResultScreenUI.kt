package com.example.thaavarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.thaavarm.api.PlantNetResponse

@Composable
fun ResultScreenUI(
    imageUri: String?,
    plantResponse: PlantNetResponse?,
    onBackClick: () -> Unit
) {
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
            imageUri?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = "Captured Image",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (plantResponse != null) {
                val plantName = plantResponse.results.firstOrNull()?.species?.scientificNameWithoutAuthor ?: "Can't be found"
                val plantFamily = plantResponse.results.firstOrNull()?.species?.family?.scientificNameWithoutAuthor ?: "Can't be found"
                Text(text = "Plant: $plantName")
                Text(text = "Family: $plantFamily")
            } else {
                CircularProgressIndicator()
            }
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
        plantResponse = null,
        onBackClick = {}
    )
}
