package com.example.retrolab.retrofit

import com.example.practice.retrofit.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @POST("/api/sendCode")
    suspend fun goEmail(@Header("email") email: String): Message

    @POST("/api/signin")
    suspend fun goIn(@Header("email") email: String, @Header("code") code: String): Token

    @Headers("Content-Type: application/json")
    @POST("/api/createProfile")
    suspend fun createProfile(@Header("Authorization") token: String, @Body profile: Profile): Profile

    @Headers("Content-Type: application/json")
    @GET("/api/catalog")
    suspend fun  getCatalog(): Response<List<Catalog>>
}