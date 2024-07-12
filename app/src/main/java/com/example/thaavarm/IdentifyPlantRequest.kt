package com.example.thaavarm.api

data class IdentifyPlantRequest(
    val organs: List<String>,
    val images: List<String>
)
