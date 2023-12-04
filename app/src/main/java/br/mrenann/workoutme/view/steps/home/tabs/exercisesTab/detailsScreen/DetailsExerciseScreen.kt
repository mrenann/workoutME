package br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.detailsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.mrenann.workoutme.resource.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.utils.uiState.UiStateExerciseDetails
import br.mrenann.workoutme.view.components.ErrorState
import br.mrenann.workoutme.view.components.Loading

internal class DetailsExerciseScreen(val id: String) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel<DetailsExerciseStepModel>()
        viewModel.getExercise(id)

        DetailsScreenContent( viewModel = viewModel)
    }
}


@Composable
private fun DetailsScreenContent(
    viewModel: DetailsExerciseStepModel
) {
    val state by viewModel.state.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val strings = LocalStrings.current

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is UiStateExerciseDetails.Loading -> {
                    Loading()
                }
                is UiStateExerciseDetails.Error -> {
                    ErrorState(message = "ERROR")
                }
                is UiStateExerciseDetails.Success -> {
                    val exerciseUI = (state as UiStateExerciseDetails.Success).exercise
                }
            }
        }
    }

}