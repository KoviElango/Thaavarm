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

    val commonNames = MutableStateFlow<List<String>>(emptyList())
    val errorMessage = MutableStateFlow<String?>(null)

    fun identifyPlant(imageFile: File, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.identifyPlant(imageFile, apiKey)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.results.isNotEmpty()) {
                        _plantResponse.value = body
                        val names = body.results.flatMap { it.species.commonNames }
                        commonNames.value = names
                        errorMessage.value = null
                    } else {
                        _plantResponse.value = null
                        commonNames.value = emptyList()
                        errorMessage.value = "Unable to identify the plant. Please try again with a different image."
                    }
                } else {
                    _plantResponse.value = null
                    commonNames.value = emptyList()
                    errorMessage.value = "Unable to identify the plant. Please try again with a different image."
                }
            } catch (e: Exception) {
                _plantResponse.value = null
                commonNames.value = emptyList()
                errorMessage.value = "An error occurred: ${e.message}. Please retry."
            }
        }
    }
}
