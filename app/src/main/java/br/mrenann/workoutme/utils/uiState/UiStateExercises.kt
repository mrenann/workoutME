package br.mrenann.workoutme.utils.uiState

import br.mrenann.workoutme.domain.model.ExerciseUI

sealed class UiStateExercises {
    object Loading : UiStateExercises()
    data class Success(val exercises: List<ExerciseUI?>) : UiStateExercises()
    data class Error(val error: Throwable) : UiStateExercises()
}