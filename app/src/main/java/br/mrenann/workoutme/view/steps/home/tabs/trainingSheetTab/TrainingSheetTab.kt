package br.mrenann.workoutme.view.steps.home.tabs.trainingSheetTab

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import br.mrenann.workoutme.view.steps.TrainingSheet.tabs.trainingSheetTab.listScreen.TrainingSheetTabFirstScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
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

        Navigator(listOf(TrainingSheetTabFirstScreen)) { navigator ->
            SlideTransition(navigator) { screen ->
                Column {
                    screen.Content()
                }
            }
        }
    }
}