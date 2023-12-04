package br.mrenann.workoutme.view.steps.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import br.mrenann.workoutme.view.steps.home.tabs.homeTab.HomeTab
import br.mrenann.workoutme.view.steps.home.tabs.TrainingSheetTab
import br.mrenann.workoutme.view.steps.home.tabs.HomeTab3
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.FileText
import compose.icons.evaicons.fill.Grid

object HomeScreen : Screen {
    @OptIn(ExperimentalVoyagerApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab, HomeTab, HomeTab)
                )
            }
        ) { tabNavigator ->
            Scaffold(
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                    ) {
                        CurrentTab()
                    }

                },
                bottomBar = {
                    Column(Modifier.navigationBarsPadding()) {
                        NavigationBar {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(TrainingSheetTab)
                            TabNavigationItem(HomeTab3)
                        }
                    }

                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current
        val selected = tabNavigator.current.key == tab.key
        val selectedIcon = when (tab.key) {
            HomeTab.key -> {
                rememberVectorPainter(EvaIcons.Fill.Grid)
            }

            TrainingSheetTab.key -> {
                rememberVectorPainter(EvaIcons.Fill.FileText)
            }

            HomeTab3.key -> {
                rememberVectorPainter(EvaIcons.Fill.Grid)
            }

            else -> {
                rememberVectorPainter(EvaIcons.Fill.Grid)
            }
        }
        NavigationBarItem(
            icon = {
                Icon(
                    painter = if (selected) selectedIcon else tab.options.icon!!,
                    contentDescription = tab.options.title
                )
            },
            label = { Text(text = tab.options.title) },
            selected = selected,
            onClick = { tabNavigator.current = tab }
        )
    }
}