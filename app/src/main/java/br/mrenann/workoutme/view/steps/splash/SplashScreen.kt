package br.mrenann.workoutme.view.steps.splash

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.mrenann.workoutme.HomeScreen
import br.mrenann.workoutme.view.steps.login.LoginScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.FirebaseAuth

object SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navigator.push(LoginScreen)
        } else {
            navigator.replace(HomeScreen)
        }
    }
}