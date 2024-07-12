package com.example.thaavarm.repository

import com.example.thaavarm.api.PlantNetApi
import com.example.thaavarm.api.PlantNetResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class PlantRepository(private val api: PlantNetApi) {
    suspend fun identifyPlant(
        organs: RequestBody,
        image: MultipartBody.Part,
        apiKey: String
    ): Response<PlantNetResponse> {
        return api.identifyPlant(organs, image, apiKey)
    }
}
