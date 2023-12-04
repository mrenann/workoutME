package br.mrenann.workoutme.utils.uiState

import br.mrenann.workoutme.domain.model.TrainingSheetUI

sealed class UiStateTrainingSheet {
    object Loading : UiStateTrainingSheet()
    data class Success(val sheetList: List<TrainingSheetUI?>) : UiStateTrainingSheet()
    data class Error(val error: Throwable) : UiStateTrainingSheet()
}