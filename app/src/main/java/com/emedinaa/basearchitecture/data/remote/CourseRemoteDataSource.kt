package com.emedinaa.basearchitecture.data.remote

import android.util.Log
import com.emedinaa.basearchitecture.CourseEntity
import com.emedinaa.basearchitecture.data.CourseRepository
import com.emedinaa.basearchitecture.data.remote.dto.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CourseRemoteDataSource(
    private val remoteApi: RemoteApi,
    private val json: Json
) : CourseRepository {

    override suspend fun retrieveCourses(): Result<List<CourseEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val response = remoteApi.callService(remoteApi.buildGetRequest("/api/courses/"))
                if (response.isSuccessful) {
                    val body = response.body?.string() ?: ""
                    Log.v("CONSOLE", "body : $body")
                    val courseResponse =
                        json.decodeFromString<CoursesResponse>(body)
                    val courses = courseResponse.data?.map {
                        CourseEntity(
                            it.id, it.nickname ?: "",
                            it.name ?: "", it.startDate ?: "",
                            it.image ?: "", it.desc ?: ""
                        )
                    } ?: emptyList()
                    Result.success(courses)
                } else {
                    Result.failure(Exception("Error ${response.code}  ${response.message}"))
                }
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
}