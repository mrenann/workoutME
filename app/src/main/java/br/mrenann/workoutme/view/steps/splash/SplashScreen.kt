package br.mrenann.workoutme.view.steps.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.mrenann.workoutme.view.steps.home.HomeScreen
import br.mrenann.workoutme.R
import br.mrenann.workoutme.view.steps.login.LoginScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

private const val DELAY_TO_NAVIGATE = 600L

internal object SplashScreen : Screen {

    @Composable
    override fun Content() {
        ContentSplashScreen()
    }

}

@Composable
private fun ContentSplashScreen() {
    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(key1 = true) {
        delay(DELAY_TO_NAVIGATE)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navigator.push(LoginScreen)
        } else {
            navigator.replace(HomeScreen)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo"
            )
        }
    }
}

@Preview
@Composable
private fun ContentPreview() {
    ContentSplashScreen()
}


