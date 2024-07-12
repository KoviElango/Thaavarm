package com.example.thaavarm

import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.thaavarm.api.RetrofitInstance
import com.example.thaavarm.repository.PlantRepository
import com.example.thaavarm.viewmodel.MainViewModel
import com.example.thaavarm.viewmodel.MainViewModelFactory

@Composable
fun ResultScreen(navController: NavController, imageUri: String?) {
    val context = LocalContext.current
    val decodedUri = Uri.parse(imageUri)
    val repository = PlantRepository(RetrofitInstance.api)
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(repository))
    val plantResponse by viewModel.plantResponse.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.identifyPlant(decodedUri)
    }

    ResultScreenUI(
        imageUri = imageUri,
        plantName = plantResponse?.results?.firstOrNull()?.species?.scientificNameWithoutAuthor ?: "Can't be found",
        plantFamily = plantResponse?.results?.firstOrNull()?.species?.family?.scientificNameWithoutAuthor ?: "Can't be found",
        onBackClick = {
            navController.navigate("camera") {
                popUpTo("camera") { inclusive = true }
            }
        }
    )
}
