package com.example.thaavarm.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thaavarm.api.PlantNetResponse
import com.example.thaavarm.repository.PlantRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class MainViewModel(private val repository: PlantRepository) : ViewModel() {
    private val _plantResponse = MutableStateFlow<PlantNetResponse?>(null)
    val plantResponse: StateFlow<PlantNetResponse?> = _plantResponse

    private val apiKey = "2b10jVHqA1F6CyulhEX3oTZekO"

    fun identifyPlant(imageUri: Uri) {
        viewModelScope.launch {
            try {
                val file = File(imageUri.path)
                val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                val body = MultipartBody.Part.createFormData("images", file.name, requestFile)
                val organsPart = RequestBody.create("text/plain".toMediaTypeOrNull(), "leaf")

                // Log API request details
                Log.d("MainViewModel", "API request: imageUri=${imageUri.path}, organs=leaf, apiKey=$apiKey")

                val response = repository.identifyPlant(organsPart, body, apiKey)
                if (response.isSuccessful) {
                    _plantResponse.value = response.body()
                    // Log successful response
                    Log.d("MainViewModel", "API response: ${response.body()}")
                } else {
                    // Handle error response
                    Log.d("MainViewModel", "API error response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "API request failed: ${e.message}")
            }
        }
    }
}
