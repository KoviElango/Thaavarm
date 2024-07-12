package com.example.thaavarm.api

import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface PlantNetApi {
    @Multipart
    @POST("v2/identify/all")
    suspend fun identifyPlant(
        @Part("organs") organs: RequestBody,
        @Part image: MultipartBody.Part,
        @Query("api-key") apiKey: String
    ): Response<PlantNetResponse>
}

//API end point to 'all' organs