package br.mrenann.workoutme.view.components.homeTab

import br.mrenann.workoutme.domain.model.TrainingSheetUI
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable

internal fun CardTrainingSheet (
    sheetUI: TrainingSheetUI = TrainingSheetUI(""),
    onPressDetails: (String) -> Unit = {}
) {
    Card(
        onClick = {onPressDetails(sheetUI.id.toString())},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            sheetUI.nome?.let {
                Text(modifier = Modifier, text = it, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            }
            sheetUI.descricao?.let {
                Text(modifier = Modifier, text = it, fontWeight = FontWeight.Thin)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                sheetUI.date?.let {
                    Text(modifier = Modifier, text = it, fontWeight = FontWeight.Thin)
                }
                sheetUI.createrId?.let {
                    if(it != sheetUI.userId)  Text(text = it)
                }
            }


        }

    }
}