package com.example.thaavarm

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.thaavarm.repository.PlantRepository
import com.example.thaavarm.viewmodel.MainViewModel
import com.example.thaavarm.viewmodel.MainViewModelFactory

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thaavarm.api.RetrofitInstance
import java.io.File

@Composable
fun ResultScreen(navController: NavController, imageUri: String?) {
    val context = LocalContext.current
    val repository = remember { PlantRepository(RetrofitInstance.api) }
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(repository))
    val plantResponse by viewModel.plantResponse.collectAsState()

    LaunchedEffect(imageUri) {
        imageUri?.let {
            val imageFile = File(Uri.parse(it).path!!)
            viewModel.identifyPlant(imageFile, "2b10jVHqA1F6CyulhEX3oTZekO")
        }
    }

    ResultScreenUI(
        imageUri = imageUri,
        plantResponse = plantResponse,
        onBackClick = { navController.popBackStack() }
    )
}
