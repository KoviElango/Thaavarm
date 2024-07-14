package com.example.thaavarm

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.thaavarm.api.RetrofitInstance
import com.example.thaavarm.repository.PlantRepository
import com.example.thaavarm.viewmodel.MainViewModel
import com.example.thaavarm.viewmodel.MainViewModelFactory
import java.io.File

@Composable
fun ResultScreen(navController: NavController, imageUri: String?) {
    val context = LocalContext.current
    val repository = remember { PlantRepository(RetrofitInstance.api) }
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(repository))
    val plantResponse by viewModel.plantResponse.collectAsState()
    val commonNames by viewModel.commonNames.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(imageUri) {
        imageUri?.let {
            val file = File(Uri.parse(it).path!!)
           // Log.d("ResultScreen", "Image File Path: ${file.absolutePath}")
            viewModel.identifyPlant(file, "2b10jVHqA1F6CyulhEX3oTZekO")
        }
    }

    ResultScreenUI(
        imageUri = imageUri,
        plantResponse = plantResponse,
        commonNames = commonNames,
        errorMessage = errorMessage,
        onBackClick = { navController.popBackStack() }
    )
}
