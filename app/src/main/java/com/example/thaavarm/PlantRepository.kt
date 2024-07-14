package com.example.thaavarm.repository

import android.util.Log
import com.example.thaavarm.ImageCompressor
import com.example.thaavarm.api.PlantNetApi
import com.example.thaavarm.api.PlantNetResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class PlantRepository(private val api: PlantNetApi) {
    private val imageCompressor = ImageCompressor()

    suspend fun identifyPlant(imageFile: File, apiKey: String): Response<PlantNetResponse> {
        val resizedImageFile = imageCompressor.resizeAndCompressImage(imageFile.path, 1280, 90)
        val requestFile = resizedImageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageBody = MultipartBody.Part.createFormData("images", resizedImageFile.name, requestFile)
        val organsBody = "leaf".toRequestBody("text/plain".toMediaTypeOrNull())
//
//        // Log request details; this can be commented but here for troubleshooting purposes
//        Log.d("PlantRepository", "Request URL: https://my-api.plantnet.org/v2/identify/all?api-key=$apiKey")
//        Log.d("PlantRepository", "Images: ${resizedImageFile.absolutePath}")
//        Log.d("PlantRepository", "Organs: leaf")
//        Log.d("PlantRepository", "Include Related Images: false")
//        Log.d("PlantRepository", "No Reject: false")
//        Log.d("PlantRepository", "Lang: en")

        return api.identifyPlant(
            images = imageBody,
            organs = organsBody,
            includeRelatedImages = false,
            noReject = false,
            lang = "en",
            apiKey = apiKey
        )
    }
}
