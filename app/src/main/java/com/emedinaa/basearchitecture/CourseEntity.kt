package com.emedinaa.basearchitecture

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseEntity(
    val id: Int,
    val nickname: String,
    val name: String,
    @SerialName("startdate") val startDate: String,
    val image: String,
    val desc: String
)