package br.mrenann.workoutme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.mrenann.workoutme.resource.LocalStrings
import br.mrenann.workoutme.resource.strings
import br.mrenann.workoutme.resource.theme.WorkoutMeTheme
import br.mrenann.workoutme.view.steps.splash.SplashScreen
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.voyager.navigator.Navigator
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

class MainActivity : ComponentActivity(), DIAware {

    override val di by closestDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val lyricist = rememberStrings(translations = strings)
            ProvideStrings(lyricist = lyricist, provider = LocalStrings) {
                BaseApp { Navigator(screen = SplashScreen) }
            }
        }
    }
}

@Composable
fun BaseApp(content: @Composable () -> Unit) {
    WorkoutMeTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseApp { }
}