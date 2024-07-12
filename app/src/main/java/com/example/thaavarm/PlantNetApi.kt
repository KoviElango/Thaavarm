package com.example.thaavarm.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface PlantNetApi {
    @Multipart
    @POST("/v2/identify/all")
    suspend fun identifyPlant(
        @Part images: MultipartBody.Part,
        @Part("organs") organs: RequestBody,
        @Part("include-related-images") includeRelatedImages: RequestBody,
        @Part("no-reject") noReject: RequestBody,
        @Part("lang") lang: RequestBody,
        @Part("api-key") apiKey: RequestBody
    ): Response<PlantNetResponse>
}
