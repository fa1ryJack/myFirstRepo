package com.example.retrolab.retrofit

import com.example.practice.retrofit.Message
import com.example.practice.retrofit.Profile
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface API {
    @POST("/api/sendCode")
    suspend fun goEmail(@Header("email") email: String): Message

    @POST("/api/signin")
    suspend fun goIn(@Header("email") email: String, @Header("code") code: String): Message

    @POST("/api/createProfile")
    suspend fun CreateProfile(@Body profile: Profile): Profile
}