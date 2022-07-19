package com.emedinaa.basearchitecture.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.basearchitecture.CourseEntity
import com.emedinaa.basearchitecture.R
import com.emedinaa.basearchitecture.data.CourseRepository
import com.emedinaa.basearchitecture.data.remote.CourseRemoteDataSource
import com.emedinaa.basearchitecture.data.remote.RemoteApi
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


class MainFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    //private val remoteApi = RemoteApi()
    private val courseRepository: CourseRepository by lazy {
        CourseRemoteDataSource(RemoteApi(), Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = CourseAdapter(emptyList())

        retrieveCourses()
    }

    private fun retrieveCourses() {
        lifecycleScope.launch {
            val result = courseRepository.retrieveCourses()

            if (result.isSuccess) {
                render(result.getOrDefault(emptyList()))
            } else {
                showError(result.exceptionOrNull())
            }
        }
    }
    /*private fun retrieveCourses() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                courseRepository.retrieveCourses()
            }
            if (result.isSuccess) {
                render(result.getOrDefault(emptyList()))
            } else {
                showError(result.exceptionOrNull())
            }
        }
    }*/

    /*private fun retrieveCourses() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                processCall()
            }
            if (result.isSuccess) {
                render(result.getOrDefault(emptyList()))
            } else {
                showError(result.exceptionOrNull())
            }
        }
    }*/

    private fun showError(throwable: Throwable?) {
        Log.v("CONSOLE", "throwable $throwable")
    }

    private fun render(courses: List<CourseEntity>) {
        Log.v("CONSOLE", "render $courses")
        (recyclerView?.adapter as? CourseAdapter)?.update(courses)
    }

    /*private fun processCall(): Result<List<CourseEntity>> {
        return try {
            val response = remoteApi.callService(remoteApi.buildGetRequest("/api/courses/"))
            if (response.isSuccessful) {
                val body = response.body?.string() ?: ""
                Log.v("CONSOLE", "body : $body")
                val courseResponse =
                    Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }.decodeFromString<CoursesResponse>(body)
                Result.success(courseResponse.data ?: emptyList())
            } else {
                Result.success(emptyList())
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }*/


}