package com.emedinaa.basearchitecture

import kotlinx.serialization.Serializable

@Serializable
data class CourseEntity(val id: String, val name: String, val image: String)