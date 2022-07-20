package com.emedinaa.basearchitecture

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MainFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private val remoteApi = RemoteApi()

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

    private fun showError(throwable: Throwable?) {
        Log.v("CONSOLE", "throwable $throwable")
    }

    private fun render(courses: List<CourseEntity>) {
        Log.v("CONSOLE", "render $courses")
        (recyclerView?.adapter as? CourseAdapter)?.update(courses)
    }

    private fun retrieveCourses() {
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
    }

    private fun processCall(): Result<List<CourseEntity>> {
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
    }

}