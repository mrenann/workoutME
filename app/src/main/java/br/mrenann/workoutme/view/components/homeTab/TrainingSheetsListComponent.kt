package br.mrenann.workoutme.view.components.homeTab

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.domain.model.TrainingSheetUI
import br.mrenann.workoutme.view.components.ErrorState
import br.mrenann.workoutme.view.components.Loading

@Composable
fun TrainingSheetsListComponent(
    loading: MutableState<Boolean>,
    error: MutableState<Boolean>,
    sheetList: List<TrainingSheetUI>,
    onCardPressed: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()

    when {
        loading.value -> Loading()
        error.value -> ErrorState(message = "ERROR")
        sheetList.isNotEmpty() -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(280.dp)
                    .verticalScroll(scrollState)
            ) {
                sheetList.map { sheet ->
                    CardTrainingSheet(sheet) {
                        onCardPressed(it)
                    }
                }
            }
        }
    }

}