package com.emedinaa.basearchitecture.data

import com.emedinaa.basearchitecture.CourseEntity

interface CourseRepository {
    suspend fun retrieveCourses(): Result<List<CourseEntity>>
}