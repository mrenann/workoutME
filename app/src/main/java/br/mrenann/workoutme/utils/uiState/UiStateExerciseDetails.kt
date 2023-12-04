package br.mrenann.workoutme.utils.uiState

import br.mrenann.workoutme.domain.model.ExerciseUI

sealed class UiStateExerciseDetails {
    object Loading : UiStateExerciseDetails()
    data class Success(val exercise: MutableMap<String, Any>?) : UiStateExerciseDetails()
    data class Error(val error: Throwable) : UiStateExerciseDetails()
}