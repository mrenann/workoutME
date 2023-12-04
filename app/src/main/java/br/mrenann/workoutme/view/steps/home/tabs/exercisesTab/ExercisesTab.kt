package br.mrenann.workoutme.view.steps.home.tabs.exercisesTab

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import br.mrenann.workoutme.view.steps.home.tabs.exercisesTab.listScreen.ExerciseTabFirstScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Compass

object exercisesTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Exercicios"
            val icon = rememberVectorPainter(EvaIcons.Outline.Compass)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(listOf(ExerciseTabFirstScreen)) { navigator ->
            SlideTransition(navigator) { screen ->
                Column {
                    screen.Content()
                }
            }
        }
    }
}