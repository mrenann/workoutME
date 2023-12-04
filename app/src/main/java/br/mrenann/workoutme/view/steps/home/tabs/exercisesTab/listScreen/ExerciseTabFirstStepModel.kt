package br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.listScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import br.mrenann.workoutme.domain.FirebaseRepositoryRemote
import br.mrenann.workoutme.domain.model.TrainingSheetUI
import br.mrenann.workoutme.resource.LocalStrings
import br.mrenann.workoutme.utils.uiState.UiStateExercises
import br.mrenann.workoutme.utils.uiState.UiStateTrainingSheet
import br.mrenann.workoutme.view.components.homeTab.TrainingSheetsListComponent
import br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.updateExerciseScreen.UpdateExerciseScreen
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

internal class ExerciseTabFirstStepModel(
    private val firebaseRepositoryRemote: FirebaseRepositoryRemote,
) : StateScreenModel<UiStateExercises>(UiStateExercises.Loading) {

    init {
        getExercisesFromDatabase()
    }

    private fun getExercisesFromDatabase() {
        screenModelScope.launch {
            try {
                mutableState.value = UiStateExercises.Success(
                    exercises = firebaseRepositoryRemote.getExercises()
                )
            }catch (error: Throwable){
                mutableState.value = UiStateExercises.Error(error)
            }
        }
    }
}