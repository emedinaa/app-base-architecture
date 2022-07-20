package com.emedinaa.basearchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emedinaa.basearchitecture.data.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * https://developer.android.com/topic/architecture/ui-layer/events#handle-viewmodel-events
 * https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
 */
class MainViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Success(emptyList()))
    val uiState: StateFlow<MainUIState> = _uiState

    private fun retrieveCourses() {
        viewModelScope.launch {
            val result = courseRepository.retrieveCourses()
            if (result.isSuccess) {
                _uiState.value = MainUIState.Success(result.getOrDefault(emptyList()))
            } else {
                _uiState.value = MainUIState.Error(result.exceptionOrNull())
            }
        }
    }

    init {
        retrieveCourses()
    }

}