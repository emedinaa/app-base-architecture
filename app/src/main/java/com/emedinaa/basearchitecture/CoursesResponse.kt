package com.emedinaa.basearchitecture

import kotlinx.serialization.Serializable

@Serializable
data class CoursesResponse(val status: Int?, val msg: String?, val data: List<CourseEntity>?)