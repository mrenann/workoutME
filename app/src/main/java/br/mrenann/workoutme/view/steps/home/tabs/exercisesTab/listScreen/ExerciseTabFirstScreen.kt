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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import br.mrenann.workoutme.domain.model.ExerciseUI
import br.mrenann.workoutme.resource.LocalStrings
import br.mrenann.workoutme.utils.uiState.UiStateExercises
import br.mrenann.workoutme.view.components.homeTab.ExercisesListComponent
import br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.updateExerciseScreen.UpdateExerciseScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.FirebaseAuth

object ExerciseTabFirstScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<ExerciseTabFirstStepModel>()
        val state by viewModel.state.collectAsState()

        ExerciseObserver(state = state)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun ExerciseObserver(
        state: UiStateExercises,
    ) {
        val listExerciseUI = remember { mutableStateListOf<ExerciseUI>() }
        val loading = remember { mutableStateOf(false) }
        val error = remember { mutableStateOf(false) }
        val currentUser = FirebaseAuth.getInstance().currentUser

        when (state) {
            is UiStateExercises.Loading -> {
                println("LOADING")
                loading.value = true
                error.value = false
            }

            is UiStateExercises.Error -> {
                println(state.error.message)
                loading.value = false
                error.value = true
            }

            is UiStateExercises.Success -> {
                listExerciseUI.clear()
                state.exercises.map { exercise ->
                    exercise?.let { exerciseUI ->
                        listExerciseUI.add(exerciseUI)
                    }
                }
                loading.value = false
                error.value = false
            }
        }

        Scaffold(
            floatingActionButton = {
                val navigator = LocalNavigator.currentOrThrow
                FloatingActionButton(
                    onClick = { navigator.push(UpdateExerciseScreen)},
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }, content = {
                TrainingSheetContent(
                    modifier = Modifier,
                    loading = loading,
                    error = error,
                    listExerciseUI = listExerciseUI
                )
            }
        )
    }

    @Composable
    private fun TrainingSheetContent(
        modifier: Modifier,
        loading: MutableState<Boolean>,
        error: MutableState<Boolean>,
        listExerciseUI: SnapshotStateList<ExerciseUI>,
    ) {
        val strings = LocalStrings.current
        val navigator = LocalNavigator.currentOrThrow

        Column(modifier) {
            ExercisesListComponent(sheetList = listExerciseUI, loading = loading, error = error, navigator = navigator)
        }
    }
}