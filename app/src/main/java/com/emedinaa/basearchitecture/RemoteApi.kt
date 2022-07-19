package com.emedinaa.basearchitecture

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

class RemoteApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://obscure-earth-55790.herokuapp.com/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    fun build(): RemoteService = retrofit.create(RemoteService::class.java)

    interface RemoteService {
        @GET("api/courses/")
        suspend fun getCourses(): Response<CoursesResponse>
    }
}