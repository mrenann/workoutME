package br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab.updateSheetScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.view.components.InputField
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel

internal object UpdateSheetScreen : Screen {

    @Composable
    override fun Content() {
        val nome = rememberSaveable { mutableStateOf("") }
        val descricao = rememberSaveable { mutableStateOf("") }
        val viewModel = rememberScreenModel<UpdateSheetStepModel>()
        val state by viewModel.state.collectAsState()

        var uri by remember{
            mutableStateOf<Uri?>(null)
        }

        val singlePhotoPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                uri = it
            }
        )

        Column(Modifier.padding(10.dp)) {
            InputField(
                valueState = nome,
                labelId = "Nome",
                keyboardType = KeyboardType.Text
            )
            InputField(
                valueState = descricao,
                labelId = "Descricao",
                keyboardType = KeyboardType.Text
            )
        }

        Button(onClick = {
            viewModel.createTrainingSheet(nome.value,descricao.value)
        }) {

        }
    }
}