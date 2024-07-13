package com.example.thaavarm

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thaavarm.repository.PlantRepository
import com.example.thaavarm.viewmodel.MainViewModel
import com.example.thaavarm.viewmodel.MainViewModelFactory
import com.example.thaavarm.api.RetrofitInstance
import java.io.File

//Change 1: API key should not be hardcoded and should be stored in a secure place

@Composable
fun ResultScreen(navController: NavController, imageUri: String?) {
    val repository = remember { PlantRepository(RetrofitInstance.api) }
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(repository))
    val plantResponse by viewModel.plantResponse.collectAsState()

    LaunchedEffect(imageUri) {
        imageUri?.let {
            val file = File(Uri.parse(it).path!!)
            Log.d("ResultScreen", "Image File Path: ${file.absolutePath}")
            viewModel.identifyPlant(file, "2b10jVHqA1F6CyulhEX3oTZekO")
        }
    }

    ResultScreenUI(
        imageUri = imageUri,
        plantResponse = plantResponse,
        onBackClick = { navController.popBackStack() }
    )
}
