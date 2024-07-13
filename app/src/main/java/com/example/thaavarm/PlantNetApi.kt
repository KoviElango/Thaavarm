package com.example.thaavarm.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PlantNetApi {
    @Multipart
    @POST("/v2/identify/all")
    suspend fun identifyPlant(
        @Part images: MultipartBody.Part,
        @Part("organs") organs: RequestBody,
        @Query("include-related-images") includeRelatedImages: Boolean,
        @Query("no-reject") noReject: Boolean,
        @Query("lang") lang: String,
        @Query("api-key") apiKey: String
    ): Response<PlantNetResponse>
}

