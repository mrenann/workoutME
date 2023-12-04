package br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.updateExerciseScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.view.components.InputField
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import coil.compose.AsyncImage

internal object UpdateExerciseScreen : Screen {
    @Composable
    override fun Content() {
        val nome = rememberSaveable { mutableStateOf("") }
        val observacoes = rememberSaveable { mutableStateOf("") }
        val viewModel = rememberScreenModel<UpdateExerciseStepModel>()
        val state by viewModel.state.collectAsState()
        val context = LocalContext.current
        var uri by remember {
            mutableStateOf<Uri?>(null)
        }

        val singlePhotoPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                uri = it
            }
        )

        Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(modifier = Modifier
                .clickable {
                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
                .size(160.dp)
                .padding(20.dp)
                .background(
                    color = if (uri == null) Color.White else Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ), model = uri, contentDescription = null)

            InputField(
                valueState = nome,
                labelId = "Nome",
                keyboardType = KeyboardType.Text
            )
            InputField(
                valueState = observacoes,
                labelId = "Observacoes",
                keyboardType = KeyboardType.Text
            )

        }

        Button(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            onClick = {
            uri?.let {
                viewModel.uploadPhotoToStorage(
                    context = context,
                    nome = nome.value,
                    observacoes = observacoes.value,
                    uri = it
                )
            }
        }) {
            Text(text = "Enviar")
        }
    }
}