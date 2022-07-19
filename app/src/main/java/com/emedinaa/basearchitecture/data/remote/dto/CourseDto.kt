package com.emedinaa.basearchitecture.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    val id: Int,
    val nickname: String?,
    val name: String?,
    @SerialName("startdate") val startDate: String?,
    val image: String?,
    val desc: String?
)