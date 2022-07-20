package com.emedinaa.basearchitecture.viewmodel

import com.emedinaa.basearchitecture.CourseEntity

sealed class MainUIState {
    data class Success(val courses: List<CourseEntity>) : MainUIState()
    data class Error(val exception: Throwable?) : MainUIState()
}
