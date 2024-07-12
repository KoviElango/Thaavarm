package com.example.thaavarm.repository

import com.example.thaavarm.api.PlantNetApi
import com.example.thaavarm.api.PlantNetResponse
import com.example.thaavarm.ImageCompressor
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Response
import java.io.File

class PlantRepository(private val api: PlantNetApi) {
    private val imageCompressor = ImageCompressor()

    suspend fun identifyPlant(imageFile: File, apiKey: String): Response<PlantNetResponse> {
        val resizedImageFile = imageCompressor.resizeAndCompressImage(imageFile.path, 1280, 90)
        val requestFile = resizedImageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageBody = MultipartBody.Part.createFormData("images", resizedImageFile.name, requestFile)
        val organs = "leaf".toRequestBody("text/plain".toMediaTypeOrNull())
        val includeRelatedImages = "false".toRequestBody("text/plain".toMediaTypeOrNull())
        val noReject = "false".toRequestBody("text/plain".toMediaTypeOrNull())
        val lang = "en".toRequestBody("text/plain".toMediaTypeOrNull())
        val apiKeyBody = apiKey.toRequestBody("text/plain".toMediaTypeOrNull())

        return api.identifyPlant(
            images = imageBody,
            organs = organs,
            includeRelatedImages = includeRelatedImages,
            noReject = noReject,
            lang = lang,
            apiKey = apiKeyBody
        )
    }
}
