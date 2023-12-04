package br.mrenann.workoutme.view.components.homeTab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.mrenann.workoutme.domain.model.ExerciseUI
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable

internal fun CardExercise(
    sheetUI: ExerciseUI = ExerciseUI(""),
    onPressDetails: (String) -> Unit = {}
) {
    Card(
        onClick = { onPressDetails(sheetUI.id.toString()) },
        modifier = Modifier
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(sheetUI.imagem).crossfade(true).build(),
                contentDescription = "",
                modifier = Modifier.width(128.dp).height(128.dp)
            )
            sheetUI.nome?.let {
                Text(modifier = Modifier, text = it, fontWeight = FontWeight.Black)
            }
            Text(text = "ABC")

        }

    }
}