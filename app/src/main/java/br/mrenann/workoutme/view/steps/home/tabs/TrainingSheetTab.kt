package br.mrenann.workoutme.view.steps.home.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.FileText

object TrainingSheetTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Fichas de Treino"
            val icon = rememberVectorPainter(EvaIcons.Outline.FileText)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Text(text = "HOME TAB 2")
    }
}