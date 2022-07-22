package com.emedinaa.basearchitecture.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoursesResponse(val status: Int?, val msg: String?, val data: List<CourseDto>?)