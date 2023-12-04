package br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.detailsScreen

import br.mrenann.workoutme.utils.uiState.UiStateExerciseDetails
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

internal class DetailsExerciseStepModel : StateScreenModel<UiStateExerciseDetails>(UiStateExerciseDetails.Loading) {


    fun getExercise(id: String) {
        screenModelScope.launch {
            try {
                FirebaseFirestore
                    .getInstance()
                    .collection("exercise").document(id).get().addOnSuccessListener { document->
                        println(document)
                        mutableState.value = UiStateExerciseDetails.Success(
                            exercise = document.data
                        )
                    }

            }catch (error: Throwable){
                mutableState.value = UiStateExerciseDetails.Error(error)
            }
        }
    }
}