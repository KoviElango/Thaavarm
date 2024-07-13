package com.example.thaavarm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thaavarm.api.PlantNetResponse
import com.example.thaavarm.repository.PlantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val repository: PlantRepository) : ViewModel() {

    private val _plantResponse = MutableStateFlow<PlantNetResponse?>(null)
    val plantResponse: StateFlow<PlantNetResponse?> = _plantResponse

    fun identifyPlant(imageFile: File, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.identifyPlant(imageFile, apiKey)
                if (response.isSuccessful) {
                    _plantResponse.value = response.body()
                } else {
                    // Handle error scenario here
                    _plantResponse.value = null
                }
            } catch (e: Exception) {
                // Handle exception here
                _plantResponse.value = null
            }
        }
    }
}
