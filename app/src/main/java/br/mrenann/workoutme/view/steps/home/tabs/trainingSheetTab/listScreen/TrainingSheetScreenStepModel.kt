package br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.listScreen

import br.mrenann.workoutme.domain.FirebaseRepositoryRemote
import br.mrenann.workoutme.utils.uiState.UiStateTrainingSheet
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch

internal class TrainingSheetScreenStepModel(
    private val firebaseRepositoryRemote: FirebaseRepositoryRemote,
) : StateScreenModel<UiStateTrainingSheet>(UiStateTrainingSheet.Loading) {

    init {
        getAllSheetsFromDatabase()
    }

    private fun getAllSheetsFromDatabase() {
        screenModelScope.launch {
            try {
                mutableState.value = UiStateTrainingSheet.Success(
                    sheetList = firebaseRepositoryRemote.getAllTrainingSheet()
                )
            }catch (error: Throwable){
                mutableState.value = UiStateTrainingSheet.Error(error)
            }
        }
    }
}