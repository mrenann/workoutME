package br.mrenann.workoutme.view.components.homeTab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.domain.model.ExerciseUI
import br.mrenann.workoutme.view.components.ErrorState
import br.mrenann.workoutme.view.components.Loading
import br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.detailsScreen.DetailsExerciseScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun ExercisesListComponent(
    loading: MutableState<Boolean>,
    error: MutableState<Boolean>,
    sheetList: List<ExerciseUI>,
    navigator: Navigator,
) {
    when {
        loading.value -> Loading()
        error.value -> ErrorState(message = "ERROR")
        sheetList.isNotEmpty() -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(280.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2 )
                ) {
                    items(sheetList) { exercise ->
                        CardExercise(exercise) {
                            exercise.id?.let{ exerciseId -> navigator.push(DetailsExerciseScreen(exerciseId)) }
                        }
                    }

                }

            }
        }
    }

}