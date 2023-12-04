package br.mrenann.workoutme.utils.uiState

import br.mrenann.workoutme.domain.model.TrainingSheetUI

sealed class UiStateUpdate {
    object Loading : UiStateUpdate()
    object Success : UiStateUpdate()
    data class Error(val error: Throwable) : UiStateUpdate()
}