package com.example.thaavarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thaavarm.api.PlantNetResponse

@Composable
fun ResultScreenUI(
    imageUri: String?,
    plantResponse: PlantNetResponse?,
    commonNames: List<String>,
    errorMessage: String?,
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
                    painter = rememberAsyncImagePainter(it),
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
                val commonNames = plantResponse.results.firstOrNull()?.species?.commonNames ?: emptyList()
                Text(text = "Plant: $plantName")
                Text(text = "Family: $plantFamily")
                if (commonNames.isNotEmpty()) {
                    Text(text = "Common Names:")
                    commonNames.forEach { name ->
                        Text(text = name)
                    }
                } else {
                    Text(text = "Common Names: Can't be found")
                }
            } else if (errorMessage != null) {
                Text(text = errorMessage)
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
        commonNames = emptyList(),
        errorMessage = null,
        onBackClick = {}
    )
}
