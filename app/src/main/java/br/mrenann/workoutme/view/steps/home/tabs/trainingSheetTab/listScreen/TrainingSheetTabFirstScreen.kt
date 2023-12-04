package br.mrenann.workoutme.view.steps.TrainingSheet.tabs.trainingSheetTab.listScreen

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
import br.mrenann.workoutme.domain.model.TrainingSheetUI
import br.mrenann.workoutme.resource.LocalStrings
import br.mrenann.workoutme.utils.uiState.UiStateTrainingSheet
import br.mrenann.workoutme.view.components.homeTab.TrainingSheetsListComponent
import br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.listScreen.TrainingSheetScreenStepModel
import br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.updateSheetScreen.UpdateSheetScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.FirebaseAuth

object TrainingSheetTabFirstScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<TrainingSheetScreenStepModel>()
        val state by viewModel.state.collectAsState()

        TrainingSheetObserver(state = state)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun TrainingSheetObserver(
        state: UiStateTrainingSheet,
    ) {
        val listTrainingSheetUI = remember { mutableStateListOf<TrainingSheetUI>() }
        val loading = remember { mutableStateOf(false) }
        val error = remember { mutableStateOf(false) }
        val currentUser = FirebaseAuth.getInstance().currentUser

        when (state) {
            is UiStateTrainingSheet.Loading -> {
                println("LOADING")
                loading.value = true
                error.value = false
            }

            is UiStateTrainingSheet.Error -> {
                println(state.error.message)
                loading.value = false
                error.value = true
            }

            is UiStateTrainingSheet.Success -> {
                println(state.sheetList)
                listTrainingSheetUI.clear()
                state.sheetList.map { sheet ->
                    sheet?.let { sheetUI ->
                        if (sheetUI.userId == currentUser?.uid) {
                            listTrainingSheetUI.add(sheetUI)
                        }
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
                    onClick = { navigator.push(UpdateSheetScreen)},
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }, content = {
                TrainingSheetContent(
                    modifier = Modifier,
                    loading = loading,
                    error = error,
                    sheetListUI = listTrainingSheetUI
                )
            }
        )
    }

    @Composable
    private fun TrainingSheetContent(
        modifier: Modifier,
        loading: MutableState<Boolean>,
        error: MutableState<Boolean>,
        sheetListUI: SnapshotStateList<TrainingSheetUI>,
    ) {
        val strings = LocalStrings.current

        Column(modifier) {
            TrainingSheetsListComponent(sheetList = sheetListUI, loading = loading, error = error) {
                println("CLICOU")
            }
        }
    }
}